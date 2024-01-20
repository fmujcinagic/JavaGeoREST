package at.tugraz.oop2;

import java.math.BigInteger;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.locationtech.jts.geom.Geometry;

public class ConstructAmenityID {
    private final LoadData loadData;
    private long totalNumberOfAmenities;

    public ConstructAmenityID(LoadData loadData) {
        this.loadData = loadData;
    }
    public Map<String, Object> getAmenityByID(long id) {
        Map<String, Object> response;
        if(loadData.getLatMap().containsKey(String.valueOf(id)) && loadData.getLonMap().containsKey(String.valueOf(id))){
            response = getAmenityByNode(id);
        } else if(loadData.getWayMap().containsKey(String.valueOf(id)) || loadData.getWayMapUnmodified().containsKey(String.valueOf(id))){
            response = getAmenityByWay(id);
        } else if(loadData.getRelationTagsMap().containsKey(String.valueOf(id)))
        {
            response = getAmenityByRelation(id);
        }
        else {
            throw new CustomNotFoundException("Entity requested is not a type of amenity");
        }
        return response;
    }

    public Map<String, Object> getAmenities(AmenityInfo amenityInfo){
        Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String,Object>> entries = chooseEntriesList(amenityInfo);
        response.put("entries", entries);
        Map<String, Object> pagingMap = new LinkedHashMap<>();
        pagingMap.put("skip", amenityInfo.getSkip());
        pagingMap.put("take", amenityInfo.getTake());
        pagingMap.put("total", totalNumberOfAmenities);
        response.put("paging", pagingMap);
        return  response;
    }

    public List<Map<String,Object>> chooseEntriesList(AmenityInfo amenityInfo)  {

        List<Map<String,Object>> entries = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        Geometry parameterPoint = null;
        Geometry searchArea = null;
        MathTransform transform = null;

        if (Objects.equals(amenityInfo.getCoordinateType(), "bbox")) {
            Coordinate[] coords = getCoordinatesForBbox(amenityInfo);
            searchArea = geometryFactory.createPolygon(coords);
        }
        else if (Objects.equals(amenityInfo.getCoordinateType(), "point")) {
            try {
                CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
                CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:31256");
                transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
            } catch (Exception e){
                throw new CustomInternalServerErrorException("Interval server error");
            }
            parameterPoint = geometryFactory.createPoint(new Coordinate(amenityInfo.getPointX(), amenityInfo.getPointY()));
            try {
                parameterPoint = (Point) JTS.transform(parameterPoint, transform);
                BigInteger bigIntegerPointD = amenityInfo.getPointD();
                double pointDAsDouble = bigIntegerPointD.doubleValue();
                searchArea = parameterPoint.buffer(pointDAsDouble);
            } catch (Exception e){
                throw new CustomInternalServerErrorException("Interval server error");
            }
        }
        System.out.println("This is searchArea in chooseEntriesList(): " + searchArea);

        List<Long> amenityIDsForEntries = new ArrayList<>();

        for (String wayId : loadData.getWayMapUnmodified().keySet()) {
            Map<String, Object> wayTags = (Map<String, Object>) loadData.getWayTagsMap().get(wayId);

            if (wayTags != null && wayTags.containsKey("amenity")) {
                Geometry wayLineStringOrPolygon;
                if(loadData.getPolygonsMap().containsKey(wayId))
                    wayLineStringOrPolygon  = constructGeometry(Long.parseLong(wayId), "Polygon");
                else
                    wayLineStringOrPolygon  = constructGeometry(Long.parseLong(wayId), "LineString");

                if (Objects.equals(amenityInfo.getCoordinateType(), "point"))
                {
                    try {
                        wayLineStringOrPolygon = JTS.transform(wayLineStringOrPolygon, transform);
                    } catch (Exception e) {
                        throw new CustomInternalServerErrorException("Interval server error");
                    }
                }

                if (searchArea != null && wayLineStringOrPolygon != null) {
                    if (searchArea.intersects(wayLineStringOrPolygon)) {

                        if (amenityInfo.getAmenityType() != null && !Objects.equals(amenityInfo.getAmenityType(), "none")) {

                            if (Objects.equals(amenityInfo.getAmenityType(), wayTags.get("amenity"))) {
                                amenityIDsForEntries.add(Long.valueOf(wayId));
                            }
                        } else {
                            amenityIDsForEntries.add(Long.valueOf(wayId));
                        }
                    }
                }
            }
        }



        for (String nodeID : loadData.getLatMap().keySet()){
            Map<String, Object> nodesTags = (Map<String, Object>) loadData.getNodesTagsMapMap().get(nodeID);
            if (nodesTags != null && nodesTags.containsKey("amenity")) {

                Geometry point = constructGeometry(Long.parseLong(nodeID), "Point");

                if (Objects.equals(amenityInfo.getCoordinateType(), "point")){
                    try {
                        point = JTS.transform(point, transform);
                    } catch (Exception e) {
                        throw new CustomInternalServerErrorException("Interval server error");
                    }
                }

                if (searchArea != null && point != null) {
                    if(searchArea.intersects(point)){
                        if (amenityInfo.getAmenityType() != null && !Objects.equals(amenityInfo.getAmenityType(), "none")) {
                            if (Objects.equals(amenityInfo.getAmenityType(), nodesTags.get("amenity"))) {
                                amenityIDsForEntries.add(Long.valueOf(nodeID));
                            }
                        } else
                            amenityIDsForEntries.add(Long.valueOf(nodeID));
                    }
                }
            }
        }

        // ovdje promjenjen pristup za geometry collection, sada checkiramo ne samo jednom nego za svaki geometry
        for (CustomRelation relation : loadData.getRelations()) {
            LinkedHashMap<String, String> relationsTagsMap = (LinkedHashMap<String, String>) loadData.getRelationTagsMap().get(relation.getId());
            if (relationsTagsMap != null && relationsTagsMap.containsKey("amenity")) {
                Geometry multiPolygon = constructGeometry(Long.parseLong(relation.getId()), "MultiPolygon");

                if (Objects.equals(amenityInfo.getCoordinateType(), "point")) {
                    try {
                        multiPolygon = JTS.transform(multiPolygon, transform);
                    } catch (Exception e) {
                        throw new CustomInternalServerErrorException("Internal server error");
                    }
                }

                if (searchArea != null && multiPolygon != null) {
                    if (multiPolygon instanceof GeometryCollection) {
                        GeometryCollection geometryCollection = (GeometryCollection) multiPolygon;
                        for (int i = 0; i < geometryCollection.getNumGeometries(); i++) {
                            Geometry geometry = geometryCollection.getGeometryN(i);
                            if (searchArea.intersects(geometry)) {
                                addAmenityIfMatches(amenityInfo, amenityIDsForEntries, relation, relationsTagsMap);
                                break;
                            }
                        }
                    } else if (searchArea.intersects(multiPolygon)) {
                        addAmenityIfMatches(amenityInfo, amenityIDsForEntries, relation, relationsTagsMap);
                    }
                }
            }
        }


        totalNumberOfAmenities = amenityIDsForEntries.size();
        long lengthOfWays = Math.min(amenityInfo.getTake(), totalNumberOfAmenities) + amenityInfo.getSkip();
        Collections.sort(amenityIDsForEntries);
        for (int count = amenityInfo.getSkip(); count < lengthOfWays; count++){
            entries.add(getAmenityByID(amenityIDsForEntries.get(count)));
        }
        return entries;
    }
    private void addAmenityIfMatches(AmenityInfo amenityInfo, List<Long> amenityIDsForEntries, CustomRelation relation, LinkedHashMap<String, String> relationsTagsMap) {
        if (amenityInfo.getAmenityType() != null && !Objects.equals(amenityInfo.getAmenityType(), "none")) {
            if (Objects.equals(amenityInfo.getAmenityType(), relationsTagsMap.get("amenity"))) {
                amenityIDsForEntries.add(Long.valueOf(relation.getId()));
            }
        } else {
            amenityIDsForEntries.add(Long.valueOf(relation.getId()));
        }
    }
    private static Coordinate[] getCoordinatesForBbox(AmenityInfo info) {
        double bboxTrX = info.getBboxBrX(); // Top-right X is the same as bottom-right X
        double bboxTrY = info.getBboxTlY(); // Top-right Y is the same as top-left Y
        double bboxBlX = info.getBboxTlX(); // Bottom-left X is the same as top-left X
        double bboxBlY = info.getBboxBrY(); // Bottom-left Y is the same as bottom-right Y

        return new Coordinate[]{
                new Coordinate(info.getBboxTlX(), info.getBboxTlY()), // Top-left
                new Coordinate(bboxTrX, bboxTrY),                     // Top-right
                new Coordinate(info.getBboxBrX(), info.getBboxBrY()), // Bottom-right
                new Coordinate(bboxBlX, bboxBlY),                     // Bottom-left
                new Coordinate(info.getBboxTlX(), info.getBboxTlY())  // Close the linear ring
        };
    }

    public Map<String, Object> getAmenityByNode(long id)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> tagsMap = (LinkedHashMap<String, String>) loadData.getNodesTagsMapMap().get(String.valueOf(id));


        response.put("id", id);

        Geometry geometry  = constructGeometry(id, "Point");

        String geoJson = convertGeometryToGeoJson(geometry);
        Map<String, Object> geoMap = convertGeoJsonToMap(geoJson);

        response.put("geom", geoMap);

        if (tagsMap != null) {
            response.put("tags", tagsMap);
            response.put("type", tagsMap.getOrDefault("amenity", ""));
            response.put("name", tagsMap.getOrDefault("name", ""));
        }
        return response;
    }

    public Map<String, Object> getAmenityByWay(long id)
    {
        Map<String, Object> response = new LinkedHashMap<>();

        Map<String, String> tagsMap = (LinkedHashMap<String, String>)loadData.getWayTagsMap().get(String.valueOf(id));

        if (!tagsMap.containsKey("amenity")) {
            throw new CustomNotFoundException("Entity requested is not a type of amenity");
        }
        response.put("id", id);
        Geometry geometry = null;
        if(loadData.getPolygonsMap().containsKey(String.valueOf(id)))
            geometry  = constructGeometry(id, "Polygon");
        else
            geometry  = constructGeometry(id, "LineString");

        String geoJson = convertGeometryToGeoJson(geometry);
        Map<String, Object> geoMap = convertGeoJsonToMap(geoJson);
        response.put("geom", geoMap);

        if (tagsMap != null) {
            response.put("tags", tagsMap);
            response.put("type", tagsMap.getOrDefault("amenity", ""));
            response.put("name", tagsMap.getOrDefault("name", ""));
        }
        return response;
    }

    public Map<String, Object> getAmenityByRelation(long id)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> tagsMap = (LinkedHashMap<String, String>) loadData.getRelationTagsMap().get(String.valueOf(id));

        if (!tagsMap.containsKey("amenity")) {
            throw new CustomNotFoundException("Entity requested is not a type of amenity");
        }
        response.put("id", id);

        Geometry geometry  = constructGeometry(id, "MultiPolygon");

        String geoJson = convertGeometryToGeoJson(geometry);
        Map<String, Object> geoMap = convertGeoJsonToMap(geoJson);
        response.put("geom", geoMap);


        if (tagsMap != null) {
            response.put("tags", tagsMap);
            response.put("type", tagsMap.getOrDefault("amenity", ""));
            response.put("name", tagsMap.getOrDefault("name", ""));
        }
        return response;
    }

    private Geometry constructGeometry(long id, String type_of_geom)
    {
        if(type_of_geom.equals("Point"))
        {
            String lat = loadData.getLatMap().get(String.valueOf(id));
            String lon = loadData.getLonMap().get(String.valueOf(id));

            if (lat != null && lon != null) {

                double latitude = Double.parseDouble(lat);
                double longitude = Double.parseDouble(lon);

                GeometryFactory factory = new GeometryFactory();
                Coordinate originalCoordinate = new Coordinate(longitude, latitude);

                Point originalPoint = factory.createPoint(originalCoordinate);

                return originalPoint;

            } else {
                GeometryFactory factory = new GeometryFactory();
                Coordinate originalCoordinate = new Coordinate();

                Point originalPoint = factory.createPoint(originalCoordinate);
                return originalPoint;
            }

        } else if (type_of_geom.equals("LineString")) {
            List<String>referenceList = loadData.getWayMapUnmodified().get(String.valueOf(id));
            List<Coordinate> coordinates = new ArrayList<>();

            for (String reference : referenceList) {
                String lat = loadData.getLatMap().get(reference);
                String lon = loadData.getLonMap().get(reference);

                if (lat != null && lon != null) {
                    double latitude = Double.parseDouble(lat);
                    double longitude = Double.parseDouble(lon);
                    coordinates.add(new Coordinate(longitude, latitude));
                }
            }
            if (coordinates.size() >= 2) {
                GeometryFactory factory = new GeometryFactory();

                LineString line = factory.createLineString(coordinates.toArray(new Coordinate[0]));
                return line;
            }
        }
        else if (type_of_geom.equals("Polygon")) {
            List<String>referenceList = loadData.getWayMapUnmodified().get(String.valueOf(id));
            List<Coordinate> coordinates = new ArrayList<>();

            for (String reference : referenceList) {
                String lat = loadData.getLatMap().get(reference);
                String lon = loadData.getLonMap().get(reference);

                if (lat != null && lon != null) {
                    double latitude = Double.parseDouble(lat);
                    double longitude = Double.parseDouble(lon);
                    coordinates.add(new Coordinate(longitude, latitude));
                }
            }
            if (coordinates.size() >= 2) {
                GeometryFactory factory = new GeometryFactory();

                return factory.createPolygon(coordinates.toArray(new Coordinate[0]));
            }
        }
        else if (type_of_geom.equals("MultiPolygon")) {
            try {
                GeometryCollection geometryCollection = loadData.getRelationGeometryMap().get(String.valueOf(id));

                if (geometryCollection != null) {
                    return geometryCollection;
                }
            } catch (Exception e) {
                throw new CustomNotFoundException("Loading the geometry for relationID");
            }
        }
        return null;
    }

    private String convertGeometryToGeoJson(Geometry geometry) {
        GeoJsonWriter writer = new GeoJsonWriter();
        return writer.write(geometry);
    }


    private Map<String, Object> convertGeoJsonToMap(String geoJson) {
        Map<String, Object> result = new LinkedHashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> geoJsonMap = objectMapper.readValue(geoJson, Map.class);
            String type = (String) geoJsonMap.get("type");
            if (type == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'type' field is missing in GeoJSON");
            }

            if ("LineString".equals(type)) {
                List<List<Double>> coordinates = (List<List<Double>>) geoJsonMap.get("coordinates");
                result.put("coordinates", coordinates);
            }
            if ("Polygon".equals(type)) {
                List<List<Double>> coordinates = (List<List<Double>>) geoJsonMap.get("coordinates");
                result.put("coordinates", coordinates);
            }
            if("GeometryCollection".equals(type)){
                List<Map<String, Object>> geometries = (List<Map<String, Object>>) geoJsonMap.get("geometries");
                result.put("geometries", geometries);
            } else {
                List<Double> coordinates = (List<Double>) geoJsonMap.get("coordinates");
                result.put("coordinates", coordinates);
            }


            Map<String, Object> crs = (Map<String, Object>) geoJsonMap.get("crs");

            result.put("type", type);
            result.put("crs", crs);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity requested could not be found", e);
        }

        return result;
    }

}

