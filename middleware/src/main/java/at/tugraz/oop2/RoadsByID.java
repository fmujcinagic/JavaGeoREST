package at.tugraz.oop2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class RoadsByID {

    @GetMapping("/roads/{id}")
    public Map<String, Object> getRoadsByID(@PathVariable Long id) {
        AmenityService roadsService = new AmenityService();
        return roadsService.getRoadsByID(id);
    }
}

