package at.tugraz.oop2;

import org.locationtech.jts.geom.*;

import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.*;

import static at.tugraz.oop2.GeometryUtils.buildMultipolygon;


public class GeometryCollectionBuild {

    private static long numOfReturns = 0;
    private static long polygonCreatedCounter = 0;
    private static long polygonNotCreatedCounter = 0;
    public static GeometryCollection buildGeom(List<RelationMember> members, Map<String, String> tags, LoadData loadData, String id) {

        GeometryFactory factory = new GeometryFactory(); // JTS GeometryFactory instance

        if (tags.containsValue("multipolygon")) {

            List<MultiPolygon> multiPolygons = new ArrayList<>();
            List<Polygon> inners = new ArrayList<>();
            Polygon outer = null;


            for (int i= 0; i < members.size(); i++) {

                Object[] closed_circle = getNextClosed(i, members, loadData, id);
                if (closed_circle != null) {
                    String lastRole = (String) closed_circle[0];
                    int lastMemberIndex = (int) closed_circle[1];
                    Polygon closedPolygon = (Polygon) closed_circle[2];
                    if ("outer".equals(lastRole)) {
                        if (outer != null) {
                            // Calling buildMultipolygon here
                            MultiPolygon multiPolygon = buildMultipolygon(outer, inners);
                            multiPolygons.add(multiPolygon);
                            inners.clear();
                        }
                        outer = closedPolygon;
                    } else if ("inner".equals(lastRole)) {
                        inners.add(closedPolygon);
                    }

                    i = lastMemberIndex; // removed incrementing here, because we have increment in the init of for loop!!
                }
            }

            // Final call to buildMultipolygon if an outer polygon is present
            if (outer != null) {
                MultiPolygon multiPolygon = buildMultipolygon(outer, inners);
                multiPolygons.add(multiPolygon);
                inners.clear();
            }


            Geometry[] geometriesArray = multiPolygons.toArray(new Geometry[0]);

            return factory.createGeometryCollection(geometriesArray);
        } else {
            return buildGeometryCollection(members, loadData);
        }
    }

    private static Object[] getNextClosed(int startIndex, List<RelationMember> members, LoadData loadData, String id) {

        Map<String, String> latMap = loadData.getLatMap();
        Map<String, String> lonMap = loadData.getLonMap();
        Map<String, Object> polygonsMap = loadData.getPolygonsMap();
        String lastRole = null;
        int lastMemberIndex = -1;
        Polygon polygon = null;
        Polygon polygon_out_of_line_strings = null;

        Map<Integer, LineString> lineStringsMap = new LinkedHashMap<>();
        LineString previousLineString = null;

        for (int i = startIndex; i < members.size(); i++) {
            RelationMember member = members.get(i);
            if ("outer".equals(member.getRole()) || "inner".equals(member.getRole())) {
                // Check if the member's ref -> way's ID exists in polygonsMap
                if (polygonsMap.containsKey(member.getRef()))
                {
                    List<String> nodeRefs = (List<String>) polygonsMap.get(member.getRef());
                    if(nodeRefs.isEmpty())
                    {
                        throw new CustomNotFoundException("Node references are empty");
                    }
                    Coordinate[] coordinates = new Coordinate[nodeRefs.size()];
                    for (int j = 0; j < nodeRefs.size(); j++) {
                        String nodeId = nodeRefs.get(j);

                        if (latMap.containsKey(nodeId) && lonMap.containsKey(nodeId)) {
                            double lon = Double.parseDouble(lonMap.get(nodeId));
                            double lat = Double.parseDouble(latMap.get(nodeId));
                            coordinates[j] = new Coordinate(lon, lat);
                        } else {
                            return null;
                        }
                    }

                    GeometryFactory geometryFactory = new GeometryFactory(); // needed to create polygons

                    LinearRing linearRing = geometryFactory.createLinearRing(new CoordinateArraySequence(coordinates)); // creates closed cycle
                    polygon = geometryFactory.createPolygon(linearRing, null); // forms a polygon from closed cycle

                    lastRole = member.getRole();
                    lastMemberIndex = i;

                    polygonCreatedCounter += 1;
                    return new Object[]{lastRole, lastMemberIndex, polygon};

                }
                else {

                    List<String> nodeRefs = loadData.getWayMapUnmodified().get(member.getRef());

                    List<Coordinate> lineStringCoordinates = new ArrayList<>();


                    if(nodeRefs != null)
                    {
                        for (String nodeId : nodeRefs) {
                            if (latMap.containsKey(nodeId) && lonMap.containsKey(nodeId)) {
                                double lon = Double.parseDouble(lonMap.get(nodeId));
                                double lat = Double.parseDouble(latMap.get(nodeId));
                                lineStringCoordinates.add(new Coordinate(lon, lat));
                            } else {
                                throw new CustomNotFoundException("Node ID not found");
                            }
                        }
                    }

                    Coordinate[] lineStringCoords = lineStringCoordinates.toArray(new Coordinate[0]);
                    GeometryFactory geometryFactory = new GeometryFactory();
                    LineString lineString = geometryFactory.createLineString(lineStringCoords);

                    lineStringsMap.put(i, lineString);


                    if (!lineStringsMap.isEmpty()) {
                        // Get the first LineString in the map
                        LineString firstLineString = lineStringsMap.values().iterator().next();


                        if(!lineString.isEmpty() && !firstLineString.isEmpty()) { // this is check also added to avoid exceptions!!
                            if (firstLineString.getCoordinates()[0].equals2D(lineString.getCoordinates()[lineString.getCoordinates().length - 1])) {

                                // Here we create a polygon from the LineStrings
                                Coordinate[] combinedCoords = new Coordinate[firstLineString.getCoordinates().length + lineStringCoords.length - 1];
                                System.arraycopy(firstLineString.getCoordinates(), 0, combinedCoords, 0, firstLineString.getCoordinates().length);
                                System.arraycopy(lineStringCoords, 1, combinedCoords, firstLineString.getCoordinates().length, lineStringCoords.length - 1);
                                polygon_out_of_line_strings = geometryFactory.createPolygon(combinedCoords);

                                lineStringsMap.clear();

                                lastRole = member.getRole();
                                lastMemberIndex = i;
                                polygonCreatedCounter++;
                                return new Object[]{lastRole, lastMemberIndex, polygon_out_of_line_strings};
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    private static GeometryCollection buildGeometryCollection(List<RelationMember> members, LoadData loadData) {
        GeometryFactory factory = new GeometryFactory();
        List<Geometry> geometries = new ArrayList<>();

        for (RelationMember member : members) {
            if ("way".equals(member.getType())) {
                Geometry wayGeometry = constructWayGeometry(member.getRef(), loadData);
                if (wayGeometry != null) {
                    geometries.add(wayGeometry);
                }
                else
                {
                    throw new CustomNotFoundException("WayGeomtry is null!");
                }
            }
        }

        Geometry[] geometriesArray = geometries.toArray(new Geometry[0]);
        return factory.createGeometryCollection(geometriesArray);
    }

    private static Geometry constructWayGeometry(String wayId, LoadData loadData) {
        List<String>referenceList = loadData.getWayMapUnmodified().get(String.valueOf(wayId));


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

            if(loadData.getPolygonsMap().containsKey(wayId))
                return factory.createPolygon(coordinates.toArray(new Coordinate[0]));
            else
                return factory.createLineString(coordinates.toArray(new Coordinate[0]));
        }

        return null;
    }
}
