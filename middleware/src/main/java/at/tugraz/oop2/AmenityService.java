package at.tugraz.oop2;

import at.tugraz.*;
import at.tugraz.Roads;

import org.springframework.stereotype.Service;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.*;
import java.util.Map;
import java.util.Objects;

@Service
public class AmenityService {
    String BACKEND_TARGET_ENV = System.getenv("JMAP_BACKEND_TARGET");
    private final EventServiceGrpc.EventServiceBlockingStub stub;
    public AmenityService(){
        String backendTarget = parseEnvBackendTarget(BACKEND_TARGET_ENV);
        try {
            ManagedChannel channel = ManagedChannelBuilder.forTarget(backendTarget)
                    .usePlaintext()
                    .build();
            this.stub = EventServiceGrpc.newBlockingStub(channel);
        } catch (Exception e) {
            throw new CustomInternalServerErrorException("Internal server error");
        }
    }
    public Map<String, Object> getRoads(RoadInfo roadInfo) {
        try {
            Roads request;
            if(Objects.equals(roadInfo.getCoordinateType(), "bbox")){
                request = Roads.newBuilder()
                        .setBboxTlX(roadInfo.getBboxTlX())
                        .setBboxTlY(roadInfo.getBboxTlY())
                        .setBboxBrX(roadInfo.getBboxBrX())
                        .setBboxBrY(roadInfo.getBboxBrY())
                        .setRoadType(roadInfo.getRoadType())
                        .setTake(roadInfo.getTake())
                        .setSkip(roadInfo.getSkip())
                        .setCoordinateType("bbox")
                        .build();
            } else{
                request = Roads.newBuilder()
                        .setPointX(roadInfo.getPointX())
                        .setPointY(roadInfo.getPointY())
                        .setRoadType(roadInfo.getRoadType())
                        .setTake(roadInfo.getTake())
                        .setSkip(roadInfo.getSkip())
                        .setCoordinateType("point")
                        .build();
            }
            Event response = stub.getRoads(request);

            List<Map<String, Object>> entries = new ArrayList<>();
            for(Entry entry :  response.getEntriesList()){
                Map<String, Object> entryMap = new LinkedHashMap<>();
                entryMap.put("name",entry.getName());
                entryMap.put("id",entry.getId());
                entryMap.put("geom",convertGeomForRoadID(entry.getGeom()));
                entryMap.put("tags", entry.getTagsMap());
                entryMap.put("type",entry.getType());
                List<Long> childIds = new ArrayList<>(entry.getChildIdsList());
                entryMap.put("child_ids", childIds);
                entries.add(entryMap);
            }
            Map<String, Object> paging = new LinkedHashMap<>();
            paging.put("skip", response.getPaging().getSkip());
            paging.put("take", response.getPaging().getTake());
            paging.put("total", response.getPaging().getTotal());
            Map<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("entries", entries);
            responseMap.put("paging", paging);
            return responseMap;
        } catch (Exception e) {
            throw new CustomNotFoundException("The entity requested could not be found");
        }
    }

    public Map<String, Object> getAmenities(AmenityInfo amenityInfo) {
        try {

            Amenity request;
            if(Objects.equals(amenityInfo.getCoordinateType(), "bbox")){
                request = Amenity.newBuilder()
                        .setBboxTlX(amenityInfo.getBboxTlX())
                        .setBboxTlY(amenityInfo.getBboxTlY())
                        .setBboxBrX(amenityInfo.getBboxBrX())
                        .setBboxBrY(amenityInfo.getBboxBrY())
                        .setAmenityType(amenityInfo.getAmenityType())
                        .setPointD(String.valueOf(amenityInfo.getPointD()))
                        .setTake(amenityInfo.getTake())
                        .setSkip(amenityInfo.getSkip())
                        .setCoordinateType("bbox")
                        .build();
            } else{
                request = Amenity.newBuilder()
                        .setPointX(amenityInfo.getPointX())
                        .setPointY(amenityInfo.getPointY())
                        .setPointD(String.valueOf(amenityInfo.getPointD()))
                        .setTake(amenityInfo.getTake())
                        .setAmenityType(amenityInfo.getAmenityType())
                        .setCoordinateType("point")
                        .setSkip(amenityInfo.getSkip())
                        .build();
            }

            // Make the gRPC call
            Event response = stub.getAmenity(request);

            List<Map<String, Object>> entries = new ArrayList<>();
            for(Entry entry :  response.getEntriesList()){
                Map<String, Object> entryMap = new LinkedHashMap<>();
                entryMap.put("name",entry.getName());
                entryMap.put("id",entry.getId());
                entryMap.put("geom",convertGeomForRoadID(entry.getGeom()));
                entryMap.put("tags", entry.getTagsMap());
                entryMap.put("type",entry.getType());
                entries.add(entryMap);
            }
            Map<String, Object> paging = new LinkedHashMap<>();
            paging.put("skip", response.getPaging().getSkip());
            paging.put("take", response.getPaging().getTake());
            paging.put("total", response.getPaging().getTotal());
            Map<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("entries", entries);
            responseMap.put("paging", paging);


            return responseMap;

        } catch (Exception e) {
            System.out.println("Exception caught in getAmenities: " + e.getMessage());
            throw new CustomNotFoundException("Not Found!!");
        }
    }




    public Map<String, Object> getAmenityById(Long id) {
        try {
            EventById request = EventById.newBuilder().setId(id).build();
            Event response = stub.eventById(request);
            Map<String, Object> responseMap = new LinkedHashMap<>();

            responseMap.put("name",response.getName());
            responseMap.put("id",response.getId());

            responseMap.put("geom", convertGeomforAmenityID(response.getGeom()));

            responseMap.put("tags", response.getTagsMap());
            responseMap.put("type",response.getType());
            return responseMap;
        } catch (Exception e) {
            throw new CustomNotFoundException("The entity requested could not be found");
        }
    }

    public Map<String, Object> getRoadsByID(Long id) {
        try {

            RoadById roadRequest = RoadById.newBuilder().setId(id).build();
            Event response = stub.roadById(roadRequest);
            Map<String, Object> responseMap = new LinkedHashMap<>();

            responseMap.put("name",response.getName());
            responseMap.put("id",response.getId());
            responseMap.put("geom",convertGeomForRoadID(response.getGeom()));
            responseMap.put("tags", response.getTagsMap());
            responseMap.put("type",response.getType());
            List<Long> childIds = response.getChildById().getChildIDList();
            responseMap.put("child_ids", childIds);

            return responseMap;
        } catch (Exception e) {
            throw new CustomNotFoundException("The entity requested could not be found");
        }
    }

    private static Map<String, Object> convertGeomforAmenityID(Geom geom) {
        Map<String, Object> geomMap = new LinkedHashMap<>();
        geomMap.put("type", geom.getType());

        if ("LineString".equals(geom.getType())) {
            List<List<Double>> coordinatesList = new ArrayList<>();
            for (int i = 0; i < geom.getCoordinatesCount(); i += 2) {
                List<Double> coordinates = Arrays.asList(geom.getCoordinates(i), geom.getCoordinates(i + 1));
                coordinatesList.add(coordinates);
            }
            geomMap.put("coordinates", coordinatesList);
        }
        else if ("Polygon".equals(geom.getType())) {
            List<List<List<Double>>> polygon = new ArrayList<>();
            List<List<Double>> ring = new ArrayList<>();

            for (int i = 0; i < geom.getCoordinatesCount(); i += 2) {
                double lat = geom.getCoordinates(i);
                double lon = geom.getCoordinates(i + 1);


                if (Double.isNaN(lat) || Double.isNaN(lon)) {
                    if (!ring.isEmpty()) {
                        polygon.add(ring);
                        ring = new ArrayList<>();
                    }
                } else {
                    List<Double> coordinates = Arrays.asList(lat, lon);
                    ring.add(coordinates);
                }
            }

            if (!ring.isEmpty()) {
                polygon.add(ring);
            }
            geomMap.put("coordinates", polygon);
        }
        else if ("Point".equals(geom.getType())) {
            List<Double> coordinate = geom.getCoordinatesList();
            geomMap.put("coordinates", coordinate);
        }

        else if ("GeometryCollection".equals(geom.getType())){
            List<Map<String, Object>> multiPolygonsList = new ArrayList<>();
            for (MultiPolygon multiPolygon : geom.getMultiPolygonsList()) {
                Map<String, Object> multiPolygonMap = new LinkedHashMap<>();
                multiPolygonMap.put("type", "MultiPolygon");
                List<List<List<List<Double>>>> polygonsList = new ArrayList<>();
                for (Polygon polygon : multiPolygon.getPolygonsList()) {
                    List<List<List<Double>>> ringsList = new ArrayList<>();
                    List<List<Double>> coordinatesList = new ArrayList<>();
                    for (Coordinate coordinate : polygon.getCoordinatesList()) {
                        coordinatesList.add(Arrays.asList(coordinate.getLongitude(), coordinate.getLatitude()));
                    }
                    ringsList.add(coordinatesList);
                    polygonsList.add(ringsList);
                }
                multiPolygonMap.put("coordinates", polygonsList);
                multiPolygonsList.add(multiPolygonMap);
            }
            geomMap.put("geometries", multiPolygonsList);
        }

        Crs crs = geom.getCrs();
        Map<String, Object> crsMap = new LinkedHashMap<>();
        crsMap.put("type", crs.getType());

        CrsProperties crsProperties = crs.getProperties();
        Map<String, Object> propertiesMap = new LinkedHashMap<>();
        propertiesMap.put("name", crsProperties.getName());
        crsMap.put("properties", propertiesMap);

        geomMap.put("crs", crsMap);
        return geomMap;
    }
    private static Map<String, Object> convertGeomForRoadID(Geom geom) {
        Map<String, Object> geomMap = new LinkedHashMap<>();
        geomMap.put("type", geom.getType());

        if ("LineString".equals(geom.getType())) {
            List<List<Double>> coordinatesList = new ArrayList<>();
            for (int i = 0; i < geom.getCoordinatesCount(); i += 2) {
                List<Double> coordinates = Arrays.asList(geom.getCoordinates(i), geom.getCoordinates(i + 1));
                coordinatesList.add(coordinates);
            }
            geomMap.put("coordinates", coordinatesList);
        }
        else if ("Point".equals(geom.getType())) {
            List<Double> coordinate = geom.getCoordinatesList();
            geomMap.put("coordinates", coordinate);
        }
        else if ("Polygon".equals(geom.getType())) {
            List<List<List<Double>>> polygon = new ArrayList<>();
            List<List<Double>> ring = new ArrayList<>();

            for (int i = 0; i < geom.getCoordinatesCount(); i += 2) {
                double lat = geom.getCoordinates(i);
                double lon = geom.getCoordinates(i + 1);


                if (Double.isNaN(lat) || Double.isNaN(lon)) {
                    if (!ring.isEmpty()) {
                        polygon.add(ring);
                        ring = new ArrayList<>();
                    }
                } else {
                    List<Double> coordinates = Arrays.asList(lat, lon);
                    ring.add(coordinates);
                }
            }

            if (!ring.isEmpty()) {
                polygon.add(ring);
            }
            geomMap.put("coordinates", polygon);
        }
        else if ("GeometryCollection".equals(geom.getType())){
            List<Map<String, Object>> multiPolygonsList = new ArrayList<>();
            for (MultiPolygon multiPolygon : geom.getMultiPolygonsList()) {
                Map<String, Object> multiPolygonMap = new LinkedHashMap<>();
                multiPolygonMap.put("type", "MultiPolygon");
                List<List<List<List<Double>>>> polygonsList = new ArrayList<>();
                for (Polygon polygon : multiPolygon.getPolygonsList()) {
                    List<List<List<Double>>> ringsList = new ArrayList<>();
                    List<List<Double>> coordinatesList = new ArrayList<>();
                    for (Coordinate coordinate : polygon.getCoordinatesList()) {
                        coordinatesList.add(Arrays.asList(coordinate.getLongitude(), coordinate.getLatitude()));
                    }
                    ringsList.add(coordinatesList);
                    polygonsList.add(ringsList);
                }
                multiPolygonMap.put("coordinates", polygonsList);
                multiPolygonsList.add(multiPolygonMap);
            }
            geomMap.put("geometries", multiPolygonsList);
        }

        Crs crs = geom.getCrs();
        if (crs != null) {
            Map<String, Object> crsMap = new LinkedHashMap<>();
            crsMap.put("type", crs.getType());

            CrsProperties crsProperties = crs.getProperties();
            if (crsProperties != null) {
                crsMap.put("properties", Collections.singletonMap("name", crsProperties.getName()));
            }

            geomMap.put("crs", crsMap);
        }

        return geomMap;
    }
    private static String parseEnvBackendTarget(String envBackendTarget){
        return Objects.requireNonNullElse(envBackendTarget, "localhost:8020");
    }

}
