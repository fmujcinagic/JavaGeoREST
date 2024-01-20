package at.tugraz.oop2;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

public class GeometryUtils {

    private static final GeometryFactory geometryFactory = new GeometryFactory();

    public static MultiPolygon buildMultipolygon(Polygon outer, List<Polygon> inners) {
        Polygon[] polygons = new Polygon[inners.size() + 1];
        polygons[0] = outer;

        for (int i = 0; i < inners.size(); i++) {
            polygons[i + 1] = inners.get(i);
        }

        return geometryFactory.createMultiPolygon(polygons);
    }
}
