
package at.tugraz.oop2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Collections;

public class ConstructRoadID {
    private final LoadData loadData;
    private int totalNumberOfRoads;
    public ConstructRoadID(LoadData loadData) {
        this.loadData = loadData;
    }
    public Map<String, Object> getRoadsByID(long id) {

        Map<String, Object> response;

        if (loadData.getWayMap().containsKey(String.valueOf(id)) || loadData.getWayMapUnmodified().containsKey(String.valueOf(id))) {
            // 4054613
            response = getRoadByWay(id);
        }
        else if(loadData.getRelationTagsMap().containsKey(String.valueOf(id)))
        {
            response = getRoadByRelation(id);
        }
        else if (loadData.getLatMap().containsKey(String.valueOf(id)) && loadData.getLonMap().containsKey(String.valueOf(id))){
            response = getRoadByNode(id);
        }
        else {
            throw new CustomNotFoundException("Entity requested is not a type of road!");
        }

        return response;

    }

    public Map<String, Object> getRoads(RoadInfo roadInfo) {
         Map<String, Object> response = new LinkedHashMap<>();
        List<Map<String,Object>> entries = chooseEntriesList(roadInfo);
        response.put("entries", entries);
        Map<String, Integer> pagingMap = new LinkedHashMap<>();
        pagingMap.put("skip", roadInfo.getSkip());
        pagingMap.put("take", roadInfo.getTake());
        pagingMap.put("total", totalNumberOfRoads); // total numbers of ways, but we display only the number of roads that we 'take' (take parameter)
        response.put("paging", pagingMap);
        return  response;
    }
    public List<Map<String,Object>> chooseEntriesList(RoadInfo info) {
        List<Map<String,Object>> entries = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();
        Geometry bboxPolygonOrPoint = null;
        /* this means that we are dealing with bbox */
        if (Objects.equals(info.getCoordinateType(), "bbox")) {
            Coordinate[] coords = getCoordinatesForBbox(info);
            bboxPolygonOrPoint = geometryFactory.createPolygon(coords);
        }
        /* this means that we are not dealing with bbox, but with point instead */
        else if (Objects.equals(info.getCoordinateType(), "point")) {
            bboxPolygonOrPoint = geometryFactory.createPoint(new Coordinate(info.getPointX(), info.getPointY()));
        }
        List<Long> roadIDsForEntries = new ArrayList<>();
        for (String wayId : loadData.getWayMapUnmodified().keySet()){
            Map<String, Object> wayTags = (Map<String, Object>) loadData.getWayTagsMap().get(wayId);
            if (wayTags != null && wayTags.containsKey("highway")) {
                Geometry wayLineString = constructGeometry(Long.parseLong(wayId), "LineString");
                if (bboxPolygonOrPoint != null && wayLineString != null) {
                    if (bboxPolygonOrPoint.intersects(wayLineString)){
                        if (info.getRoadType() != null && !Objects.equals(info.getRoadType(), "none")) {
                            if (Objects.equals(info.getRoadType(), wayTags.get("highway"))) {
                                roadIDsForEntries.add(Long.valueOf(wayId));
                            }
                        } else
                            roadIDsForEntries.add(Long.valueOf(wayId));
                    }
                }
            }
        }
        for (String nodeID : loadData.getLatMap().keySet()){
            Map<String, Object> nodesTags = (Map<String, Object>) loadData.getNodesTagsMapMap().get(nodeID);
            if (nodesTags != null && nodesTags.containsKey("highway")) {
                Geometry point = constructGeometry(Long.parseLong(nodeID), "Point");
                if (bboxPolygonOrPoint != null && point != null) {
                    if(bboxPolygonOrPoint.intersects(point)){
                        if (info.getRoadType() != null && !Objects.equals(info.getRoadType(), "none")) {
                            if (Objects.equals(info.getRoadType(), nodesTags.get("highway"))) {
                                roadIDsForEntries.add(Long.valueOf(nodeID));
                            }
                        } else
                            roadIDsForEntries.add(Long.valueOf(nodeID));
                    }
                }
            }
        }
        for (CustomRelation relation : loadData.getRelations()){
            if (relation.getTags() != null && relation.getTags().containsKey("highway")) {
                Geometry multiPolygon = constructGeometry(Long.parseLong(relation.getId()), "MultiPolygon");
                if (bboxPolygonOrPoint != null && multiPolygon != null) {
                    if(bboxPolygonOrPoint.intersects(multiPolygon)){
                        if (info.getRoadType() != null && !Objects.equals(info.getRoadType(), "none")) {
                            if (Objects.equals(info.getRoadType(), relation.getTags().get("highway"))) {
                                roadIDsForEntries.add(Long.valueOf(relation.getId()));
                            }
                        } else
                            roadIDsForEntries.add(Long.valueOf(relation.getId()));
                    }
                }
            }
        }
        totalNumberOfRoads = roadIDsForEntries.size();
        int lengthOfWays = Math.min(info.getTake(), totalNumberOfRoads) + info.getSkip();
        Collections.sort(roadIDsForEntries);
        for (int count = info.getSkip(); count < lengthOfWays; count++){
            entries.add(getRoadsByID(roadIDsForEntries.get(count)));
        }
        return entries;
    }

    private static Coordinate[] getCoordinatesForBbox(RoadInfo info) {
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

    public Map<String, Object> getRoadByNode(long id)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> tagsMap = (LinkedHashMap<String, String>) loadData.getNodesTagsMapMap().get(String.valueOf(id));

        if (!tagsMap.containsKey("highway")) {
            throw new CustomNotFoundException("Entity requested is not a type of highway!");
        }

        response.put("id", id);

        Geometry geometry  = constructGeometry(id, "Point");

        String geoJson = convertGeometryToGeoJson(geometry);
        Map<String, Object> geoMap = convertGeoJsonToMap(geoJson);

        response.put("geom", geoMap);

        response.put("tags", tagsMap);
        response.put("type", tagsMap.getOrDefault("highway", ""));
        response.put("name", tagsMap.getOrDefault("name", ""));

        List<Long> childIds = Collections.singletonList(id);
        response.put("child_ids", childIds);

        return response;
    }

    public Map<String, Object> getRoadByWay(long id) {
        Map<String, Object> response = new LinkedHashMap<>();

        LinkedHashMap<String, String> tagsMap = (LinkedHashMap<String, String>) loadData.getWayTagsMap().get(String.valueOf(id));

        if (!tagsMap.containsKey("highway")) {
            throw new CustomNotFoundException("Entity requested is not a type of highway!");
        }
        response.put("id", id);


        Geometry geometry = constructGeometry(id, "LineString");

        String geoJson = convertGeometryToGeoJson(geometry);
        Map<String, Object> geoMap = convertGeoJsonToMap(geoJson);

        response.put("geom", geoMap);

        response.put("tags", tagsMap);
        response.put("type", tagsMap.getOrDefault("highway", ""));
        response.put("name", tagsMap.getOrDefault("name", ""));

        List<String> referenceList = loadData.getWayMapUnmodified().get(String.valueOf(id));
        List<Long> childIds = referenceList.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        response.put("child_ids", childIds);

        return response;
    }

    public Map<String, Object> getRoadByRelation(long id) {
        Map<String, Object> response = new LinkedHashMap<>();

        LinkedHashMap<String, String> tagsMap = (LinkedHashMap<String, String>) loadData.getRelationTagsMap().get(String.valueOf(id));

        if (!tagsMap.containsKey("highway")) {
            throw new CustomNotFoundException("Entity requested is not a type of highway!");
        }
        response.put("id", id);


        Geometry geometry = constructGeometry(id, "MultiPolygon");

        String geoJson = convertGeometryToGeoJson(geometry);
        Map<String, Object> geoMap = convertGeoJsonToMap(geoJson);


        response.put("geom", geoMap);

        response.put("tags", tagsMap);
        response.put("type", tagsMap.getOrDefault("highway", ""));
        response.put("name", tagsMap.getOrDefault("name", ""));



        List<CustomRelation> relations = loadData.getRelations();
        List<Long> allNodeReferences = new ArrayList<>();

        for (CustomRelation relation : relations) {
            if (Long.parseLong(relation.getId()) == id) {
                List<RelationMember> members = relation.getMembers();

                for (RelationMember member : members) {
                    if ("way".equals(member.getType())) {
                        allNodeReferences.add(Long.valueOf(member.getRef()));
                    }
                }
            }
        }

        response.put("child_ids", allNodeReferences);
        return response;
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

            // Check if the type is LineString
            if ("LineString".equals(type)) {
                // For LineString, the coordinates are an array of arrays
                List<List<Double>> coordinates = (List<List<Double>>) geoJsonMap.get("coordinates");
                result.put("coordinates", coordinates);
            } else if("GeometryCollection".equals(type)){
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

    public Geometry constructGeometry(long id, String type_of_geom) {
        if (type_of_geom.equals("LineString")) {
            List<String> referenceList = loadData.getWayMapUnmodified().get(String.valueOf(id));
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
                return factory.createLineString(coordinates.toArray(new Coordinate[0]));
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
        } else if(type_of_geom.equals("Point"))
        {
            String lat = loadData.getLatMap().get(String.valueOf(id));
            String lon = loadData.getLonMap().get(String.valueOf(id));

            if (lat != null && lon != null) {

                double latitude = Double.parseDouble(lat);
                double longitude = Double.parseDouble(lon);

                GeometryFactory factory = new GeometryFactory();
                Coordinate originalCoordinate = new Coordinate(longitude, latitude);
                return factory.createPoint(originalCoordinate);

            } else {
                GeometryFactory factory = new GeometryFactory();
                Coordinate originalCoordinate = new Coordinate();
                return factory.createPoint(originalCoordinate);
            }

        }
        return null;
    }
}

