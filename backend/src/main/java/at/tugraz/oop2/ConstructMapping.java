package at.tugraz.oop2;

import java.util.*;

import org.checkerframework.checker.units.qual.C;
import org.locationtech.jts.geom.*;
public class ConstructMapping {
    private final LoadData loadData;

    public ConstructMapping(LoadData loadData) {
        this.loadData = loadData;
    }

    public Map<String, Object> getMapping(MappingInfo mappingInfo) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coords = getCoordinatesForSearchArea(mappingInfo);
        Geometry searchArea = geometryFactory.createPolygon(coords);
        System.out.println("This is searchArea: "  + searchArea);


        List<Object> listOfPrimaries = new ArrayList<>();
        List<Object> listOfSecondaries = new ArrayList<>();
        List<Object> listOfRoads = new ArrayList<>();
        List<Object> listOfMotorways = new ArrayList<>();
        List<Object> listOfTrunks = new ArrayList<>();
        List<Object> listOfForest = new ArrayList<>();
        List<Object> listOfResidential = new ArrayList<>();
        List<Object> listOfVineyard = new ArrayList<>();
        List<Object> listOfGrass = new ArrayList<>();
        List<Object> listOfRailway = new ArrayList<>();
        List<Object> listOfWater = new ArrayList<>();

        ConstructRoadID constructGeometryObj = new ConstructRoadID(loadData);
        List<Long> numberOfIDSToDraw = new ArrayList<>();
        for(String layer : mappingInfo.getLayers())
        {
            for (String wayId : loadData.getWayMapUnmodified().keySet()) {
                Map<String, Object> wayTags = (Map<String, Object>) loadData.getWayTagsMap().get(wayId);
                if (wayTags != null && wayTags.containsKey("highway")) {
                    String stringValue = (String) wayTags.get("highway");
                    if(stringValue.equals(layer)){
                        Geometry wayLineStringOrPolygon;
                        if(loadData.getPolygonsMap().containsKey(wayId))
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "Polygon");
                        else
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "LineString");
                        if (searchArea != null && wayLineStringOrPolygon != null) {
                            if(searchArea.intersects(wayLineStringOrPolygon))
                            {
                                List<String> nodes = loadData.getWayMapUnmodified().get(wayId);
                                List<List<Double>> coordinates = new ArrayList<>();
                                for (String node : nodes) {
                                    String lat = loadData.getLatMap().get(node);
                                    String lon = loadData.getLonMap().get(node);
                                    if (lat != null && lon != null) {
                                        coordinates.add(Arrays.asList(Double.parseDouble(lat), Double.parseDouble(lon)));
                                    }
                                }
                                switch (layer) {
                                    case "primary":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfPrimaries.add(coordinates);
                                        break;
                                    case "secondary":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfSecondaries.add(coordinates);
                                        break;
                                    case "motorway":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfMotorways.add(coordinates);
                                        break;
                                    case "trunk":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfTrunks.add(coordinates);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                    }
                    if (Objects.equals(layer, "road")){
                        Geometry wayLineStringOrPolygon;
                        if(loadData.getPolygonsMap().containsKey(wayId))
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "Polygon");
                        else
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "LineString");
                        if (searchArea != null && wayLineStringOrPolygon != null) {
                            if(searchArea.intersects(wayLineStringOrPolygon))
                            {
                                List<String> nodes = loadData.getWayMapUnmodified().get(wayId);
                                List<List<Double>> coordinates = new ArrayList<>();
                                for (String node : nodes) {
                                    String lat = loadData.getLatMap().get(node);
                                    String lon = loadData.getLonMap().get(node);
                                    if (lat != null && lon != null) {
                                        coordinates.add(Arrays.asList(Double.parseDouble(lat), Double.parseDouble(lon)));
                                    }
                                }
                                numberOfIDSToDraw.add(Long.valueOf(wayId));
                                listOfRoads.add(coordinates);
                            }
                        }
                    }
                }
                if (wayTags != null && wayTags.containsKey("landuse")) {
                    String stringValue = (String) wayTags.get("landuse");
                    if(stringValue.equals(layer)){
                        Geometry wayLineStringOrPolygon;
                        if(loadData.getPolygonsMap().containsKey(wayId))
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "Polygon");
                        else
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "LineString");
                        if (searchArea != null && wayLineStringOrPolygon != null) {
                            if(searchArea.intersects(wayLineStringOrPolygon))
                            {
                                List<String> nodes = loadData.getWayMapUnmodified().get(wayId);
                                List<List<Double>> coordinates = new ArrayList<>();
                                for (String node : nodes) {
                                    String lat = loadData.getLatMap().get(node);
                                    String lon = loadData.getLonMap().get(node);
                                    if (lat != null && lon != null) {
                                        coordinates.add(Arrays.asList(Double.parseDouble(lat), Double.parseDouble(lon)));
                                    }
                                }
                                switch (layer) {
                                    case "forest":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfForest.add(coordinates);
                                        break;
                                    case "residential":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfResidential.add(coordinates);
                                        break;
                                    case "vineyard":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfVineyard.add(coordinates);
                                        break;
                                    case "grass":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfGrass.add(coordinates);
                                        break;
                                    case "railway":
                                        numberOfIDSToDraw.add(Long.valueOf(wayId));
                                        listOfRailway.add(coordinates);
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                    }
                }
                if (wayTags != null && wayTags.containsKey("water")) {
                    if (Objects.equals(layer, "water")){
                        Geometry wayLineStringOrPolygon;
                        if(loadData.getPolygonsMap().containsKey(wayId))
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "Polygon");
                        else
                            wayLineStringOrPolygon = constructGeometryObj.constructGeometry(Long.parseLong(wayId), "LineString");
                        if (searchArea != null && wayLineStringOrPolygon != null) {
                            if (searchArea.intersects(wayLineStringOrPolygon)) {
                                List<String> nodes = loadData.getWayMapUnmodified().get(wayId);
                                List<List<Double>> coordinates = new ArrayList<>();
                                for (String node : nodes) {
                                    String lat = loadData.getLatMap().get(node);
                                    String lon = loadData.getLonMap().get(node);
                                    if (lat != null && lon != null) {
                                        coordinates.add(Arrays.asList(Double.parseDouble(lat), Double.parseDouble(lon)));
                                    }
                                }
                                numberOfIDSToDraw.add(Long.valueOf(wayId));
                                listOfWater.add(coordinates);
                            }

                        }
                    }
                }
            }
            for (String relationID : loadData.getRelationTagsMap().keySet()) {
                Map<String, Object> relationTagsMap = (Map<String, Object>) loadData.getRelationTagsMap().get(relationID);
                if (relationTagsMap != null && relationTagsMap.containsKey("highway")) {
                    if (Objects.equals(layer, "road")){
                        numberOfIDSToDraw.add(Long.valueOf(relationID));
                        fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfRoads);
                    }
                }
                if (relationTagsMap != null && relationTagsMap.containsKey("water")) {
                    if (Objects.equals(layer, "water")){
                        numberOfIDSToDraw.add(Long.valueOf(relationID));
                        fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfWater);
                    }
                }
                if (relationTagsMap != null && relationTagsMap.containsKey("landuse")) {
                    String stringValue = (String) relationTagsMap.get("landuse");
                    if(stringValue.equals(layer)){
                        switch (layer) {
                            case "forest":
                                numberOfIDSToDraw.add(Long.valueOf(relationID));
                                fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfForest);
                                break;
                            case "residential":
                                numberOfIDSToDraw.add(Long.valueOf(relationID));
                                fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfResidential);
                                break;
                            case "vineyard":
                                numberOfIDSToDraw.add(Long.valueOf(relationID));
                                fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfVineyard);
                                break;
                            case "grass":
                                numberOfIDSToDraw.add(Long.valueOf(relationID));
                                fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfGrass);
                                break;
                            case "railway":
                                numberOfIDSToDraw.add(Long.valueOf(relationID));
                                fillListOfMultiPolygons(searchArea,constructGeometryObj,relationID,listOfRailway);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }


        }



        Map<String, Object> result = new LinkedHashMap<>();
        result.put("primary",listOfPrimaries);
        result.put("secondary",listOfSecondaries);
        result.put("road",listOfRoads);
        result.put("motorway",listOfMotorways);
        result.put("trunk",listOfTrunks);
        result.put("water",listOfWater);
        result.put("forest",listOfForest);
        result.put("residential",listOfResidential);
        result.put("vineyard",listOfVineyard);
        result.put("grass",listOfGrass);
        result.put("railway",listOfRailway);
        MapLogger.backendLogMapEntities(numberOfIDSToDraw);
        return result;
    }

    private void fillListOfMultiPolygons(Geometry searchArea, ConstructRoadID constructGeometryObj, String relationID, List<Object> listOfRoadSegments)
    {
        Geometry multiPolygon = constructGeometryObj.constructGeometry(Long.parseLong(relationID), "MultiPolygon");
        if (searchArea != null && multiPolygon != null) {
            if(searchArea.intersects(multiPolygon))
            {
                List<CustomRelation> relations = loadData.getRelations();
                for (CustomRelation relation : relations) {
                    if (Objects.equals(relation.getId(), relationID)) {

                        List<RelationMember> members = relation.getMembers();

                        for (RelationMember member : members) {
                            List<String> nodeReferences = (List<String>) loadData.getPolygonsMap().get(member.getRef());
                            if (nodeReferences != null) {
                                List<List<Double>> coordinates = new ArrayList<>();
                                for (String nodeID : nodeReferences)
                                {
                                    String lat = loadData.getLatMap().get(nodeID);
                                    String lon = loadData.getLonMap().get(nodeID);
                                    if (lat != null && lon != null) {
                                        coordinates.add(Arrays.asList(Double.parseDouble(lat), Double.parseDouble(lon)));
                                    }
                                    listOfRoadSegments.add(coordinates);

                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private static Coordinate[] getCoordinatesForSearchArea(MappingInfo mappingInfo) {
        double minLon = mappingInfo.getBounds().get(0);
        double minLat = mappingInfo.getBounds().get(1);
        double maxLon = mappingInfo.getBounds().get(2);
        double maxLat = mappingInfo.getBounds().get(3);
        return new Coordinate[]{
                new Coordinate(minLon, maxLat), // Top-left
                new Coordinate(maxLon, maxLat), // Top-right
                new Coordinate(maxLon, minLat), // Bottom-right
                new Coordinate(minLon, minLat), // Bottom-left
                new Coordinate(minLon, maxLat)  // Close the linear ring (Top-left again)
        };
    }
}