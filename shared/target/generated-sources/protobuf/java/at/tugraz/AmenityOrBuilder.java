// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapservice.proto

package at.tugraz;

public interface AmenityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:eventservice.Amenity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>double bboxTlX = 1;</code>
   * @return The bboxTlX.
   */
  double getBboxTlX();

  /**
   * <code>double bboxTlY = 2;</code>
   * @return The bboxTlY.
   */
  double getBboxTlY();

  /**
   * <code>double bboxBrX = 3;</code>
   * @return The bboxBrX.
   */
  double getBboxBrX();

  /**
   * <code>double bboxBrY = 4;</code>
   * @return The bboxBrY.
   */
  double getBboxBrY();

  /**
   * <code>double pointX = 5;</code>
   * @return The pointX.
   */
  double getPointX();

  /**
   * <code>double pointY = 6;</code>
   * @return The pointY.
   */
  double getPointY();

  /**
   * <code>string pointD = 7;</code>
   * @return The pointD.
   */
  java.lang.String getPointD();
  /**
   * <code>string pointD = 7;</code>
   * @return The bytes for pointD.
   */
  com.google.protobuf.ByteString
      getPointDBytes();

  /**
   * <code>int64 take = 8;</code>
   * @return The take.
   */
  long getTake();

  /**
   * <code>string amenityType = 9;</code>
   * @return The amenityType.
   */
  java.lang.String getAmenityType();
  /**
   * <code>string amenityType = 9;</code>
   * @return The bytes for amenityType.
   */
  com.google.protobuf.ByteString
      getAmenityTypeBytes();

  /**
   * <code>string coordinateType = 10;</code>
   * @return The coordinateType.
   */
  java.lang.String getCoordinateType();
  /**
   * <code>string coordinateType = 10;</code>
   * @return The bytes for coordinateType.
   */
  com.google.protobuf.ByteString
      getCoordinateTypeBytes();

  /**
   * <code>int32 skip = 11;</code>
   * @return The skip.
   */
  int getSkip();
}
