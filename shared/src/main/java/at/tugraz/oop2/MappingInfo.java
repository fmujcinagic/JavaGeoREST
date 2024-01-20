package at.tugraz.oop2;

import java.util.List;

public class MappingInfo {
    private List<String> layers;
    private List<Double> bounds;

    private int x;
    private int y;
    private int z;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public List<String> getLayers() {
        return layers;
    }

    public void setLayers(List<String> layers) {
        this.layers = layers;
    }

    public List<Double>  getBounds() {
        return bounds;
    }

    public void setBounds(List<Double> bounds) {
        this.bounds = bounds;
    }
}
