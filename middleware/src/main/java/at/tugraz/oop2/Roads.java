package at.tugraz.oop2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class Roads {
    @GetMapping("/roads")
    public Map<String, Object> getRoads(
            @RequestParam(name = "bbox.tl.x", required = false) Double bboxTlX,
            @RequestParam(name = "bbox.tl.y", required = false) Double bboxTlY,
            @RequestParam(name = "bbox.br.x", required = false) Double bboxBrX,
            @RequestParam(name = "bbox.br.y", required = false) Double bboxBrY,
            @RequestParam(name = "point.x", required = false) Double pointX,
            @RequestParam(name = "point.y", required = false) Double pointY,
            @RequestParam(name = "road", required = false, defaultValue = "none") String roadType,
            @RequestParam(name = "take", required = false, defaultValue = "50") Integer take,
            @RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip) {
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
        try {
            RoadInfo roadInfo = new RoadInfo();
            if (bboxProvided){
                roadInfo.setBboxTlX(bboxTlX);
                roadInfo.setBboxTlY(bboxTlY);
                roadInfo.setBboxBrX(bboxBrX);
                roadInfo.setBboxBrY(bboxBrY);
                roadInfo.setCoordinateType("bbox");
            } else {
                roadInfo.setPointX(pointX);
                roadInfo.setPointY(pointY);
                roadInfo.setCoordinateType("point");
            }
            roadInfo.setRoadType(roadType);
            roadInfo.setTake(take);
            roadInfo.setSkip(skip);
            AmenityService roadsService = new AmenityService();
            return roadsService.getRoads(roadInfo);
        }catch (Exception e){
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
