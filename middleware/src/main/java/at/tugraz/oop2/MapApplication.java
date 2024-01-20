package at.tugraz.oop2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class MapApplication {
    public static void main(String[] args) {
        int port = parseEnvPort(System.getenv("JMAP_MIDDLEWARE_PORT"));
        String backendTarget = parseEnvBackendTarget(System.getenv("JMAP_BACKEND_TARGET"));
        MapLogger.middlewareStartup(port,backendTarget);
        SpringApplication.run(MapApplication.class,"--server.port=" + port);
    }


    private static int parseEnvPort(String envPort){
        try {
            return Integer.parseInt(envPort);
        } catch (NumberFormatException exception) {
            return 8010;
        }
    }
    private static String parseEnvBackendTarget(String envBackendTarget){
        return Objects.requireNonNullElse(envBackendTarget, "localhost:8020");
    }


}
