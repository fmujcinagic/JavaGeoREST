package at.tugraz.oop2;

public class RoadInfo {
    private double bboxTlX;
    private double bboxTlY;
    private double bboxBrX;
    private double bboxBrY;
    private double pointX;
    private double pointY;
    private String roadType;
    private int  take;
    private int skip;
    private String coordinateType;

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public double getBboxTlX() {
        return bboxTlX;
    }

    public void setBboxTlX(double bboxTlX) {
        this.bboxTlX = bboxTlX;
    }

    public double getBboxTlY() {
        return bboxTlY;
    }

    public void setBboxTlY(double bboxTlY) {
        this.bboxTlY = bboxTlY;
    }

    public double getBboxBrX() {
        return bboxBrX;
    }

    public void setBboxBrX(double bboxBrX) {
        this.bboxBrX = bboxBrX;
    }

    public double getBboxBrY() {
        return bboxBrY;
    }

    public void setBboxBrY(double bboxBrY) {
        this.bboxBrY = bboxBrY;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
}

