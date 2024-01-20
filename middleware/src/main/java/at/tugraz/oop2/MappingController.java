package at.tugraz.oop2;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

@RestController
public class MappingController {
    @GetMapping("/tile/{z}/{x}/{y}.png")
    public void getTile(@PathVariable int z, @PathVariable int x, @PathVariable int y,
                        @RequestParam(name = "layers", required = false, defaultValue = "motorway") String layers, HttpServletResponse response) throws IOException {
        List<Double> bounds = calculateTileBounds(z, x, y);
        BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHints(Map.of(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON, RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        setupGraphicsContext(graphics, "background");

        MappingInfo mappingInfo = new MappingInfo();
        mappingInfo.setLayers(getAllLayers(layers));
        mappingInfo.setBounds(bounds);
        mappingInfo.setX(x);
        mappingInfo.setY(y);
        mappingInfo.setZ(z);
        MappingService mappingService = new MappingService();
        Map<String, Object> mappingResponseList = mappingService.getMapping(mappingInfo);


        for (String layer : layers.split(",")) {
            setupGraphicsContext(graphics, layer);
            List<List<List<Double>>> layerData = (List<List<List<Double>>>) mappingResponseList.get(layer);
            drawLayer(graphics, layer, bounds, layerData, z);
        }
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }
    private List<String> getAllLayers(String string){ return new ArrayList<>(Arrays.asList(string.split(","))); }
    private void drawLayer(Graphics2D graphics, String layer, List<Double> bounds, List<List<List<Double>>> layerData, int z) {
        for (List<List<Double>> linestring : layerData) {
            if (linestring.size() < 2) {
                continue;
            }
            if(isPolygon(linestring)){
                int[] xPoints = new int[linestring.size()];
                int[] yPoints = new int[linestring.size()];

                int i = 0;
                for (List<Double> coord : linestring) {
                    Point point = geoCoordsToTilePixels(coord.get(0), coord.get(1), z, 512, bounds);
                    xPoints[i] = point.x;
                    yPoints[i] = point.y;
                    i++;
                }

                graphics.fillPolygon(xPoints, yPoints, linestring.size());
            }
            else{
                Iterator<List<Double>> it = linestring.iterator();
                List<Double> firstCoord = it.next();
                Point prevPoint = geoCoordsToTilePixels(firstCoord.get(0), firstCoord.get(1), z, 512, bounds);

                while (it.hasNext()) {
                    List<Double> nextCoord = it.next();
                    Point currentPoint = geoCoordsToTilePixels(nextCoord.get(0), nextCoord.get(1), z, 512, bounds);
                    graphics.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
                    prevPoint = currentPoint;
                }
            }
        }
    }
    private boolean isPolygon(List<List<Double>> linestring) {
        if (linestring != null && !linestring.isEmpty()) {
            List<Double> firstElement = linestring.get(0);
            List<Double> lastElement = linestring.get(linestring.size() - 1);
            if (firstElement != null && lastElement != null && firstElement.size() >= 2 && lastElement.size() >= 2) {
                return firstElement.get(0).equals(lastElement.get(0)) && firstElement.get(1).equals(lastElement.get(1));
            }
        }
        return false;
    }
    public Point geoCoordsToTilePixels(double lat, double lon, int zoom, int tileSize, List<Double> bounds) {
        double lonMin = bounds.get(0);
        double latMin = bounds.get(1);
        double lonMax = bounds.get(2);
        double latMax = bounds.get(3);

        double xPercent = (Math.min(Math.max(lon, lonMin), lonMax) - lonMin) / (lonMax - lonMin);
        double yPercent = (latMax - Math.min(Math.max(lat, latMin), latMax)) / (latMax - latMin);

        int pixelX = (int) (xPercent * tileSize);
        int pixelY = (int) (yPercent * tileSize);
        return new Point(pixelX, pixelY);
    }


    // Idea taken from : https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#Java
    // The code in 'Tile bounding box' section was modified
    private List<Double> calculateTileBounds(int z, int x, int y) {
        double lon1 = convertTileToLon(x, z);
        double lat1 = convertTileToLat(y, z);
        double lon2 = convertTileToLon(x + 1, z);
        double lat2 = convertTileToLat(y + 1, z);

        double lonMargin = (lon2 - lon1) * 0.001;
        double latMargin = (lat1 - lat2) * 0.001;

        double lonMin = lon1 - lonMargin;
        double lonMax = lon2 + lonMargin;
        double latMin = lat2 - latMargin;
        double latMax = lat1 + latMargin;

        lonMin = Math.max(lonMin, -180);
        lonMax = Math.min(lonMax, 180);
        latMin = Math.max(latMin, -90);
        latMax = Math.min(latMax, 90);

        List<Double> bounds = new ArrayList<>();
        bounds.add(lonMin);
        bounds.add(latMin);
        bounds.add(lonMax);
        bounds.add(latMax);

        return bounds;
    }
    // Idea taken from : https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#Java
    public static double convertTileToLon(int x, int z) {
        return x / Math.pow(2.0, z) * 360.0 - 180;
    }
    // Idea taken from : https://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#Java
    public static double convertTileToLat(int y, int z) {
        double n = Math.PI - 2.0 * Math.PI * y / Math.pow(2.0, z);
        return Math.toDegrees(Math.atan(Math.sinh(n)));
    }
    private void setupGraphicsContext(Graphics2D graphics, String layer) {
        switch (layer) {
            case "motorway":
                graphics.setColor(new Color(255, 0, 0)); // Red
                graphics.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                break;
            case "trunk":
                graphics.setColor(new Color(255, 140, 0)); // Darkorange
                graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                break;
            case "primary":
                graphics.setColor(new Color(255, 165, 0)); // Orange
                graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                break;
            case "secondary":
                graphics.setColor(new Color(255, 255, 0)); // Yellow
                graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                break;
            case "road":
                graphics.setColor(new Color(128, 128, 128)); // Darkgray
                graphics.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                break;
            case "forest":
                graphics.setColor(new Color(173, 209, 158)); // Forest
                break;
            case "residential":
                graphics.setColor(new Color(223, 233, 233)); // Residential
                break;
            case "vineyard":
                graphics.setColor(new Color(172, 224, 161)); // Vineyard
                break;
            case "grass":
                graphics.setColor(new Color(205, 235, 176)); // Grass
                break;
            case "railway":
                graphics.setColor(new Color(235, 219, 233)); // Railway
                break;
            case "water":
                graphics.setColor(new Color(0, 128, 255)); // Water
                break;
            case "background":
                graphics.setColor(new Color(255, 255, 255)); // White
                graphics.fillRect(0, 0, 512, 512); // Fill the background
                break;
            default:
                break;
        }
    }


}
