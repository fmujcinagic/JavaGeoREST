package at.tugraz.oop2;

import java.math.BigInteger;

public class AmenityInfo {
    private double pointX;
    private double pointY;
    private BigInteger pointD;
    private long take;



    private double bboxTlX;
    private double bboxTlY;
    private double bboxBrX;
    private double bboxBrY;

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

    private String amenityType;
    private String coordinateType;
    private int skip;

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

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


    public BigInteger getPointD() {
        return pointD;
    }

    public void setPointD(BigInteger pointD) {
        this.pointD = pointD;
    }
    public long getTake() {
        return take;
    }

    public void setTake(long take) {
        this.take = take;
    }

    public String getAmenityType() {
        return amenityType;
    }

    public void setAmenityType(String amenityType) {
        this.amenityType = amenityType;
    }
}
