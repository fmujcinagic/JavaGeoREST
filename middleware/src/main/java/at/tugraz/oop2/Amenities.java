package at.tugraz.oop2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
import java.util.Map;


@RestController
public class Amenities {

    @GetMapping("/amenities")
    public Map<String, Object> getAmenities(@RequestParam(name = "bbox.tl.x", required = false) Double bboxTlX,
                                            @RequestParam(name = "bbox.tl.y", required = false) Double bboxTlY,
                                            @RequestParam(name = "bbox.br.x", required = false) Double bboxBrX,
                                            @RequestParam(name = "bbox.br.y", required = false) Double bboxBrY,
                                            @RequestParam(name = "point.x", required = false) Double pointX,
                                            @RequestParam(name = "point.y", required = false) Double pointY,
                                            @RequestParam(name = "point.d", required = false, defaultValue = "-1") BigInteger pointD,
                                            @RequestParam(name = "take", required = false, defaultValue = "50") long take,
                                            @RequestParam(name = "amenity", required = false, defaultValue = "none") String amenityType,
                                            @RequestParam(name = "skip", required = false, defaultValue = "0") int skip){

        boolean bboxProvided = (bboxTlX != null && bboxTlY != null && bboxBrX != null && bboxBrY != null);
        boolean pointProvided = (pointX != null && pointY != null);
        if (bboxProvided == pointProvided) {
            throw new CustomBadRequestException("Missing or invalid parameters or invalid combinations of parameters");
        }
        if (bboxProvided) {
            if (isValidLatitude(bboxTlY) || isValidLatitude(bboxBrY) || isValidLongitude(bboxTlX) || isValidLongitude(bboxBrX)) {
                throw new CustomBadRequestException("Invalid BBox coordinates!");
            }
        }
        if(!bboxProvided){
            if(pointD.signum() == -1)
                throw new CustomBadRequestException("Argument out of bounds!");
            if (isValidLatitude(pointY) || isValidLongitude(pointX)) {
                throw new CustomBadRequestException("Invalid Point coordinates (out of bounds)!");
            }
        }
        try {
            AmenityInfo amenityInfo = new AmenityInfo();
            if (bboxProvided){
                amenityInfo.setBboxTlX(bboxTlX);
                amenityInfo.setBboxTlY(bboxTlY);
                amenityInfo.setBboxBrX(bboxBrX);
                amenityInfo.setBboxBrY(bboxBrY);
                amenityInfo.setCoordinateType("bbox");
                pointD = BigInteger.valueOf(0L);
            } else {
                amenityInfo.setPointX(pointX);
                amenityInfo.setPointY(pointY);
                amenityInfo.setCoordinateType("point");
            }
            amenityInfo.setPointD(pointD);
            amenityInfo.setTake(take);
            amenityInfo.setAmenityType(amenityType);
            amenityInfo.setSkip(skip);

            AmenityService amenity = new AmenityService();

            return amenity.getAmenities(amenityInfo);

        } catch (Exception e) {
            throw new CustomInternalServerErrorException("Internal server error");
        }
    }
    private boolean isValidLatitude(Double latitude) {
        return !(latitude >= -90.0) || !(latitude <= 90.0);
    }

    private boolean isValidLongitude(Double longitude) {
        return !(longitude >= -180.0) || !(longitude <= 180.0);
    }

}