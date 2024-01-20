package at.tugraz.oop2;

import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
public class AmenityID {

    @GetMapping("/amenities/{id}")
    public Map<String, Object> getAmenityById(@PathVariable("id") Long id) {
        AmenityService amenityService = new AmenityService();
        return amenityService.getAmenityById(id);
    }
}
