package at.tugraz.oop2;

import at.tugraz.*;

import org.springframework.stereotype.Service;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.*;
import java.util.Map;
import java.util.Objects;

@Service
public class MappingService {
    String BACKEND_TARGET_ENV = System.getenv("JMAP_BACKEND_TARGET");
    private final EventServiceGrpc.EventServiceBlockingStub stub;

    public MappingService() {
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
    private static String parseEnvBackendTarget(String envBackendTarget) {
        return Objects.requireNonNullElse(envBackendTarget, "localhost:8020");
    }
    public Map<String, Object> getMapping(MappingInfo mappingInfo) {
        try {
            MappingRequest request = MappingRequest.newBuilder()
                    .setX(mappingInfo.getX())
                    .setY(mappingInfo.getY())
                    .setZ(mappingInfo.getZ())
                    .addAllBounds(mappingInfo.getBounds())
                    .addAllLayers(mappingInfo.getLayers())
                    .build();
            Event response = stub.getMapping(request);

            Map<String, Object> results = new LinkedHashMap<>();

            List<List<List<Double>>> primaryLines = new ArrayList<>();
            for (RoadSegment primarySegment : response.getPrimaryRoadsList()) {
                List<List<Double>> primaryLine = new ArrayList<>();
                for (Coordinate coord : primarySegment.getCoordinatesList()) {
                    primaryLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                primaryLines.add(primaryLine);
            }
            results.put("primary", primaryLines);

            List<List<List<Double>>> secondaryLines = new ArrayList<>();
            for (RoadSegment secondarySegment : response.getSecondaryRoadsList()) {
                List<List<Double>> secondaryLine = new ArrayList<>();
                for (Coordinate coord : secondarySegment.getCoordinatesList()) {
                    secondaryLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                secondaryLines.add(secondaryLine);
            }
            results.put("secondary", secondaryLines);

            List<List<List<Double>>> roadLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getRoadRoadsList()) {
                List<List<Double>> roadLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    roadLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                roadLines.add(roadLine);
            }
            results.put("road", roadLines);

            List<List<List<Double>>> motorwayLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getMotorwayRoadsList()) {
                List<List<Double>> motorwayLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    motorwayLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                motorwayLines.add(motorwayLine);
            }
            results.put("motorway", motorwayLines);

            List<List<List<Double>>> trunkLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getTrunkRoadsList()) {
                List<List<Double>> trunkLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    trunkLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                trunkLines.add(trunkLine);
            }
            results.put("trunk", trunkLines);

            List<List<List<Double>>> waterLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getWaterRoadsList()) {
                List<List<Double>> waterLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    waterLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                waterLines.add(waterLine);
            }
            results.put("water", waterLines);

            List<List<List<Double>>> forestLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getForestRoadsList()) {
                List<List<Double>> forestLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    forestLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                forestLines.add(forestLine);
            }
            results.put("forest", forestLines);

            List<List<List<Double>>> residentialLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getResidentialRoadsList()) {
                List<List<Double>> residentialLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    residentialLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                residentialLines.add(residentialLine);
            }
            results.put("residential", residentialLines);

            List<List<List<Double>>> vineyardLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getVineyardRoadsList()) {
                List<List<Double>> vineyardLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    vineyardLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                vineyardLines.add(vineyardLine);
            }
            results.put("vineyard", vineyardLines);

            List<List<List<Double>>> grassLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getGrassRoadsList()) {
                List<List<Double>> grassLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    grassLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                grassLines.add(grassLine);
            }
            results.put("grass", grassLines);

            List<List<List<Double>>> railwayLines = new ArrayList<>();
            for (RoadSegment roadSegment : response.getRailwayRoadsList()) {
                List<List<Double>> railwayLine = new ArrayList<>();
                for (Coordinate coord : roadSegment.getCoordinatesList()) {
                    railwayLine.add(Arrays.asList(coord.getLatitude(), coord.getLongitude()));
                }
                railwayLines.add(railwayLine);
            }
            results.put("railway", railwayLines);

            return results;

        } catch (Exception e) {
            throw new CustomNotFoundException("The entity requested could not be found");
        }
    }
}