package at.tugraz.oop2;

import at.tugraz.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class MapServiceServer {
    private static final Logger logger = Logger.getLogger(MapServiceServer.class.getName());


    public static void main(String[] args) {
        logger.info("Starting backend...");
        int port = parseEnvPort(System.getenv("JMAP_BACKEND_PORT"));
        String OSMFile = getEnvironmentOrDefault("JMAP_BACKEND_OSMFILE", "data/graz_tiny_reduced.osm");
        MapLogger.backendStartup(port, OSMFile);
        LoadData loadData = new LoadData(OSMFile);
        loadData.initData();
        MapLogger.backendLoadFinished(loadData.getNodeCount(), loadData.getWayCount(), loadData.getRelations().size());
        MapServiceServer server = new MapServiceServer();
        server.startGrpcServer(port, loadData);
    }

    private void startGrpcServer(int port, LoadData loadData){
        try{
            EventServiceImpl eventService = new EventServiceImpl();
            eventService.setLoadData(loadData);
            Server server = ServerBuilder.forPort(port)
                    .addService(eventService)
                    .build()
                    .start();
            server.awaitTermination();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal issues, i.e. cannot reach the backend.", e);
        }
    }

    static class EventServiceImpl extends EventServiceGrpc.EventServiceImplBase {
        private  LoadData loadData;

        public void setLoadData(LoadData loadData) {
            this.loadData = loadData;
        }
        @Override
        public void roadById(RoadById request, StreamObserver<Event> responseObserver)
        {
            ConstructRoadID constructRoadID = new ConstructRoadID(loadData);

            Map<String, Object> roadsData = constructRoadID.getRoadsByID(request.getId());

            Object idValue = roadsData.get("id");

            MapLogger.backendLogRoadRequest((int)request.getId());

            if (idValue != null) {
                Event.Builder eventBuilder = buildRoadLogic(roadsData,idValue);
                Event event = eventBuilder.build();
                responseObserver.onNext(event);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Roads data for the given ID is not available")
                        .asRuntimeException());
            }

        }
        public Event.Builder buildAmenityLogic(Map<String, Object> amenityData, Object idValue){
            Event.Builder eventBuilder = Event.newBuilder()
                    .setId((long) idValue)
                    .setName((String) amenityData.get("name"))
                    .setType((String) amenityData.get("type"));

            Map<String, String> tagsMap = (Map<String, String>) amenityData.get("tags");

            if (tagsMap != null) {
                eventBuilder.putAllTags(tagsMap);
            }

            Map<String, Object> geomMap = (Map<String, Object>) amenityData.get("geom");
            if (geomMap != null) {
                Geom.Builder geomBuilder = Geom.newBuilder()
                        .setType((String) geomMap.get("type"));

                if ("LineString".equals(geomMap.get("type"))) {
                    List<List<Double>> coordinates = (List<List<Double>>) geomMap.get("coordinates");
                    for (List<Double> coordinate : coordinates) {
                        geomBuilder.addAllCoordinates(coordinate);
                    }
                }
                else if ("Polygon".equals(geomMap.get("type"))) {
                    List<List<List<Double>>> polygons = (List<List<List<Double>>>) geomMap.get("coordinates");
                    if (polygons != null) {
                        for (List<List<Double>> ring : polygons) {
                            for (List<Double> coordPair : ring) {
                                geomBuilder.addAllCoordinates(coordPair);
                            }
                            // we need this for middleware
                            geomBuilder.addCoordinates(Double.NaN);
                            geomBuilder.addCoordinates(Double.NaN);
                        }
                    }

                }
                else if ("Point".equals(geomMap.get("type"))) {
                    List<Double> coordinates = (List<Double>) geomMap.get("coordinates");
                    if (coordinates != null && coordinates.size() >= 2) {
                        geomBuilder.addAllCoordinates(coordinates.subList(0, 2));
                    }
                }

                else if ("GeometryCollection".equals(geomMap.get("type"))) {
                    List<Map<String, Object>> geometries = (List<Map<String, Object>>) geomMap.get("geometries");
                    for (Map<String, Object> geometry : geometries) {
                        if ("MultiPolygon".equals(geometry.get("type"))) {
                            List<List<List<List<Double>>>> multiPolygonCoordinates = (List<List<List<List<Double>>>>) geometry.get("coordinates");
                            MultiPolygon.Builder multiPolygonBuilder = MultiPolygon.newBuilder();
                            for (List<List<List<Double>>> polygon : multiPolygonCoordinates) {
                                Polygon.Builder polygonBuilder = Polygon.newBuilder();
                                for (List<List<Double>> ring : polygon) {
                                    for (List<Double> coord : ring) {
                                        double longitude = coord.get(0);
                                        double latitude = coord.get(1);
                                        Coordinate.Builder coordinateBuilder = Coordinate.newBuilder()
                                                .setLongitude(longitude)
                                                .setLatitude(latitude);
                                        polygonBuilder.addCoordinates(coordinateBuilder);
                                    }
                                }
                                multiPolygonBuilder.addPolygons(polygonBuilder);
                            }
                            geomBuilder.addMultiPolygons(multiPolygonBuilder);
                        }
                    }
                }


                Map<String, Object> crsMap = (Map<String, Object>) geomMap.get("crs");
                if (crsMap != null)
                {
                    Crs.Builder crsBuilder = Crs.newBuilder()
                            .setType((String) crsMap.get("type"));

                    CrsProperties.Builder crsPropertiesBuilder = CrsProperties.newBuilder();
                    Map<String, Object> propertiesMap = (Map<String, Object>) crsMap.get("properties");
                    if (propertiesMap != null) {
                        String crsName = (String) propertiesMap.get("name");
                        if (crsName != null) {
                            crsPropertiesBuilder.setName(crsName);
                        }
                    }

                    crsBuilder.setProperties(crsPropertiesBuilder);
                    geomBuilder.setCrs(crsBuilder);
                }
                eventBuilder.setGeom(geomBuilder);
            }
            return eventBuilder;
        }
        public Event.Builder buildRoadLogic(Map<String, Object> roadsData, Object idValue){
            Event.Builder eventBuilder = Event.newBuilder()
                    .setId((long) idValue)
                    .setName((String) roadsData.get("name"))
                    .setType((String) roadsData.get("type"));

            Map<String, String> tagsMap = (Map<String, String>) roadsData.get("tags");

            if (tagsMap != null) {
                eventBuilder.putAllTags(tagsMap);
            }

            Map<String, Object> geomMap = (Map<String, Object>) roadsData.get("geom");
            if (geomMap != null) {
                Geom.Builder geomBuilder = Geom.newBuilder()
                        .setType((String) geomMap.get("type"));

                if ("LineString".equals(geomMap.get("type"))) {
                    List<List<Double>> coordinates = (List<List<Double>>) geomMap.get("coordinates");
                    for (List<Double> coordinate : coordinates) {
                        geomBuilder.addAllCoordinates(coordinate);
                    }
                }


                else if ("GeometryCollection".equals(geomMap.get("type"))) {
                    List<Map<String, Object>> geometries = (List<Map<String, Object>>) geomMap.get("geometries");
                    for (Map<String, Object> geometry : geometries) {
                        if ("MultiPolygon".equals(geometry.get("type"))) {
                            List<List<List<List<Double>>>> multiPolygonCoordinates = (List<List<List<List<Double>>>>) geometry.get("coordinates");
                            MultiPolygon.Builder multiPolygonBuilder = MultiPolygon.newBuilder();
                            for (List<List<List<Double>>> polygon : multiPolygonCoordinates) {
                                Polygon.Builder polygonBuilder = Polygon.newBuilder();
                                for (List<List<Double>> ring : polygon) {
                                    for (List<Double> coord : ring) {
                                        double longitude = coord.get(0);
                                        double latitude = coord.get(1);
                                        Coordinate.Builder coordinateBuilder = Coordinate.newBuilder()
                                                .setLongitude(longitude)
                                                .setLatitude(latitude);
                                        polygonBuilder.addCoordinates(coordinateBuilder);
                                    }
                                }
                                multiPolygonBuilder.addPolygons(polygonBuilder);
                            }
                            geomBuilder.addMultiPolygons(multiPolygonBuilder);
                        }
                    }
                }
                else if ("Point".equals(geomMap.get("type"))) {
                    List<Double> coordinates = (List<Double>) geomMap.get("coordinates");
                    if (coordinates != null && coordinates.size() >= 2) {
                        geomBuilder.addAllCoordinates(coordinates.subList(0, 2));
                    }
                }

                Map<String, Object> crsMap = (Map<String, Object>) geomMap.get("crs");
                if (crsMap != null) {
                    Crs.Builder crsBuilder = Crs.newBuilder()
                            .setType((String) crsMap.get("type"));

                    CrsProperties.Builder crsPropertiesBuilder = CrsProperties.newBuilder();
                    Map<String, Object> propertiesMap = (Map<String, Object>) crsMap.get("properties");
                    if (propertiesMap != null) {
                        String crsName = (String) propertiesMap.get("name");
                        if (crsName != null) {
                            crsPropertiesBuilder.setName(crsName);
                        }
                    }

                    crsBuilder.setProperties(crsPropertiesBuilder);
                    geomBuilder.setCrs(crsBuilder);
                }

                eventBuilder.setGeom(geomBuilder);
            } else {
                Geom.Builder emptyGeomBuilder = Geom.newBuilder();
                eventBuilder.setGeom(emptyGeomBuilder);
            }

            List<Long> childIds = (List<Long>) roadsData.get("child_ids");
            if (childIds != null) {
                ChildIds.Builder childIdsBuilder = ChildIds.newBuilder()
                        .addAllChildID(childIds);
                eventBuilder.setChildById(childIdsBuilder);
            }
            return eventBuilder;
        }


        @Override
        public void getAmenity(Amenity request, StreamObserver<Event> responseObserver) {
            ConstructAmenityID constructAmenities = new ConstructAmenityID(loadData);
            Map<String, Object> amenitiesData = constructAmenities.getAmenities(getAmenityInfo(request));
            MapLogger.backendLogAmenitiesRequest();
            List<Map<String, Object>> entries = (List<Map<String, Object>>) amenitiesData.get("entries");
            Event.Builder eventBuilder = Event.newBuilder();
            if (entries != null) {
                for(Map<String, Object> entry : entries)
                {   Object ID = entry.get("id");
                    Event.Builder amenityEventBuilder = buildAmenityLogic(entry, ID);
                    Event amenityEvent = amenityEventBuilder.build();

                    Entry.Builder entryBuilder = Entry.newBuilder();
                    entryBuilder.setId(amenityEvent.getId())
                            .setName(amenityEvent.getName())
                            .setType(amenityEvent.getType())
                            .putAllTags(amenityEvent.getTagsMap())
                            .setGeom(amenityEvent.getGeom());

                    eventBuilder.addEntries(entryBuilder);
                }
            }

            Map<String, Object> paging = (Map<String, Object>) amenitiesData.get("paging");
            Paging.Builder pagingBuilder = Paging.newBuilder()
                    .setSkip((Integer) paging.get("skip")).
                    setTake((Long) paging.get("take")).
                    setTotal((Long) paging.get("total"));

            eventBuilder.setPaging(pagingBuilder);

            Event event = eventBuilder.build();
            responseObserver.onNext(event);
            responseObserver.onCompleted();
        }

        @Override
        public void getMapping(MappingRequest request, StreamObserver<Event> responseObserver) {
            ConstructMapping constructMapping = new ConstructMapping(loadData);
            MappingInfo mappingInfo = getMappingInfo(request);
            MapLogger.backendLogMapRequest(mappingInfo.getX(), mappingInfo.getY(), mappingInfo.getZ(), mappingInfo.getLayers());

            Map<String, Object> mappingData = constructMapping.getMapping(mappingInfo);
            Event.Builder eventBuilder = Event.newBuilder();
            if(mappingData != null) {
                List<List<List<Double>>> listOfPrimaries = (List<List<List<Double>>>) mappingData.get("primary");
                List<List<List<Double>>> listOfSecondaries = (List<List<List<Double>>>) mappingData.get("secondary");
                List<List<List<Double>>> listOfRoads = (List<List<List<Double>>>) mappingData.get("road");
                List<List<List<Double>>> listOfMotorways = (List<List<List<Double>>>) mappingData.get("motorway");
                List<List<List<Double>>> listOfTrunks = (List<List<List<Double>>>) mappingData.get("trunk");
                List<List<List<Double>>> listOfWaters = (List<List<List<Double>>>) mappingData.get("water");
                List<List<List<Double>>> listOfForests = (List<List<List<Double>>>) mappingData.get("forest");
                List<List<List<Double>>> listOfResidential = (List<List<List<Double>>>) mappingData.get("residential");
                List<List<List<Double>>> listOfVineyard = (List<List<List<Double>>>) mappingData.get("vineyard");
                List<List<List<Double>>> listOfGrass = (List<List<List<Double>>>) mappingData.get("grass");
                List<List<List<Double>>> listOfRailways = (List<List<List<Double>>>) mappingData.get("railway");

                addRoadsToBuilder(listOfPrimaries,eventBuilder,"primary");
                addRoadsToBuilder(listOfSecondaries,eventBuilder,"secondary");
                addRoadsToBuilder(listOfRoads,eventBuilder,"road");
                addRoadsToBuilder(listOfMotorways,eventBuilder,"motorway");
                addRoadsToBuilder(listOfTrunks,eventBuilder,"trunk");

                addRoadsToBuilder(listOfWaters,eventBuilder,"water");
                addRoadsToBuilder(listOfForests,eventBuilder,"forest");
                addRoadsToBuilder(listOfResidential,eventBuilder,"residential");
                addRoadsToBuilder(listOfVineyard,eventBuilder,"vineyard");
                addRoadsToBuilder(listOfGrass,eventBuilder,"grass");
                addRoadsToBuilder(listOfRailways,eventBuilder,"railway");

            }

            Event event = eventBuilder.build();
            responseObserver.onNext(event);
            responseObserver.onCompleted();
        }
        private void addRoadsToBuilder(List<List<List<Double>>> listOfRoads, Event.Builder eventBuilder, String layer) {
            for (List<List<Double>> type : listOfRoads) {
                RoadSegment.Builder roadSegmentBuilder = RoadSegment.newBuilder();
                for (List<Double> coordinatePair : type) {
                    Coordinate coord = Coordinate.newBuilder()
                            .setLatitude(coordinatePair.get(0))
                            .setLongitude(coordinatePair.get(1))
                            .build();
                    roadSegmentBuilder.addCoordinates(coord);
                }
                switch (layer){
                    case "primary":
                        eventBuilder.addPrimaryRoads(roadSegmentBuilder.build());
                        break;
                    case "secondary":
                        eventBuilder.addSecondaryRoads(roadSegmentBuilder.build());
                        break;
                    case "road":
                        eventBuilder.addRoadRoads(roadSegmentBuilder.build());
                        break;
                    case "motorway":
                        eventBuilder.addMotorwayRoads(roadSegmentBuilder.build());
                        break;
                    case "trunk":
                        eventBuilder.addTrunkRoads(roadSegmentBuilder.build());
                        break;
                    case "water":
                        eventBuilder.addWaterRoads(roadSegmentBuilder.build());
                        break;
                    case "forest":
                        eventBuilder.addForestRoads(roadSegmentBuilder.build());
                        break;
                    case "residential":
                        eventBuilder.addResidentialRoads(roadSegmentBuilder.build());
                        break;
                    case "vineyard":
                        eventBuilder.addVineyardRoads(roadSegmentBuilder.build());
                        break;
                    case "grass":
                        eventBuilder.addGrassRoads(roadSegmentBuilder.build());
                        break;
                    case "railway":
                        eventBuilder.addRailwayRoads(roadSegmentBuilder.build());
                        break;
                }
            }
        }

        @Override
        public void getRoads(Roads request, StreamObserver<Event> responseObserver) {
            ConstructRoadID constructRoads = new ConstructRoadID(loadData);
            Map<String, Object> roadsData = constructRoads.getRoads(getRoadInfo(request));
            MapLogger.backendLogRoadsRequest();
            List<Map<String, Object>> entries = (List<Map<String, Object>>) roadsData.get("entries");
            Event.Builder eventBuilder = Event.newBuilder();
            if (entries != null) {
                for(Map<String, Object> entry : entries)
                {   Object ID = entry.get("id");
                    Event.Builder roadEventBuilder = buildRoadLogic(entry, ID);
                    Event roadEvent = roadEventBuilder.build();

                    // Build an Entry from the roadEvent
                    Entry.Builder entryBuilder = Entry.newBuilder();
                    entryBuilder.setId(roadEvent.getId())
                            .setName(roadEvent.getName())
                            .setType(roadEvent.getType())
                            .putAllTags(roadEvent.getTagsMap())
                            .setGeom(roadEvent.getGeom());

                    for (int i = 0; i < roadEvent.getChildById().getChildIDCount(); i++) {
                        entryBuilder.addChildIds(roadEvent.getChildById().getChildID(i));
                    }
                    eventBuilder.addEntries(entryBuilder);
                }
            }

            Map<String, Integer> paging = (Map<String, Integer>) roadsData.get("paging");
            Paging.Builder pagingBuilder = Paging.newBuilder()
                    .setSkip((Integer) paging.get("skip")).
                    setTake((Integer)paging.get("take")).
                    setTotal((Integer) paging.get("total"));

            eventBuilder.setPaging(pagingBuilder);

            Event event = eventBuilder.build();
            responseObserver.onNext(event);
            responseObserver.onCompleted();
        }

        private MappingInfo getMappingInfo(MappingRequest request) {
            MappingInfo mappingInfo = new MappingInfo();
            mappingInfo.setBounds(request.getBoundsList());
            mappingInfo.setLayers(request.getLayersList());
            mappingInfo.setX(request.getX());
            mappingInfo.setY(request.getY());
            mappingInfo.setZ(request.getZ());
            return mappingInfo;
        }

        //Populating RoadInfo object in order to easier pass 7 arguments to getRoads() function//

        private RoadInfo getRoadInfo(Roads request) {
            RoadInfo roadInfo = new RoadInfo();
            roadInfo.setBboxTlX(request.getBboxTlX());
            roadInfo.setBboxTlY(request.getBboxTlY());
            roadInfo.setBboxBrX(request.getBboxBrX());
            roadInfo.setBboxBrY(request.getBboxBrY());
            roadInfo.setRoadType(request.getRoadType());
            roadInfo.setTake((int) request.getTake());
            roadInfo.setSkip((int) request.getSkip());
            roadInfo.setPointX(request.getPointX());
            roadInfo.setPointY(request.getPointY());
            roadInfo.setCoordinateType((String) request.getCoordinateType());
            return roadInfo;
        }

        private AmenityInfo getAmenityInfo(Amenity request){
            AmenityInfo amenityInfo = new AmenityInfo();
            amenityInfo.setPointX(request.getPointX());
            amenityInfo.setPointY(request.getPointY());
            amenityInfo.setPointD(new BigInteger(request.getPointD()));
            amenityInfo.setAmenityType(request.getAmenityType());
            amenityInfo.setBboxTlX(request.getBboxTlX());
            amenityInfo.setBboxTlY(request.getBboxTlY());
            amenityInfo.setBboxBrX(request.getBboxBrX());
            amenityInfo.setBboxBrY(request.getBboxBrY());
            amenityInfo.setCoordinateType(request.getCoordinateType());
            amenityInfo.setSkip(request.getSkip());
            amenityInfo.setTake(request.getTake());



            return  amenityInfo;
        }

        @Override
        public void eventById(EventById request, StreamObserver<Event> responseObserver) {

            ConstructAmenityID constructAmenityID = new ConstructAmenityID(loadData);
            Map<String, Object> amenityData = constructAmenityID.getAmenityByID(request.getId());
            Object idValue = amenityData.get("id");
            MapLogger.backendLogAmenityRequest((int)request.getId());

            if (idValue != null) {
                Event.Builder eventBuilder = buildAmenityLogic(amenityData, idValue);
                Event event = eventBuilder.build();

                responseObserver.onNext(event);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.INVALID_ARGUMENT
                        .withDescription("Amenity data for the given ID is not available")
                        .asRuntimeException());
            }
        }

    }

    private static int parseEnvPort(String envPort) {
        try {
            return Integer.parseInt(envPort);
        } catch (NumberFormatException | NullPointerException exception) {
            return 8020; // we are returning default value
        }
    }

    public static String getEnvironmentOrDefault(String environmentVariable, String defaultValue) {
        try {
            String environmentValue = System.getenv(environmentVariable);
            File OSMFileToOpen = new File(environmentValue);
            if (OSMFileToOpen.exists()) {
                return environmentValue;
            } else
                return defaultValue;
        } catch (NullPointerException exception) {
            return defaultValue;
        }
    }
}


