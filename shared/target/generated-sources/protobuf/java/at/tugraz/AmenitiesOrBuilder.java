// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapservice.proto

package at.tugraz;

public interface AmenitiesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:eventservice.Amenities)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated double coordinates = 1;</code>
   * @return A list containing the coordinates.
   */
  java.util.List<java.lang.Double> getCoordinatesList();
  /**
   * <code>repeated double coordinates = 1;</code>
   * @return The count of coordinates.
   */
  int getCoordinatesCount();
  /**
   * <code>repeated double coordinates = 1;</code>
   * @param index The index of the element to return.
   * @return The coordinates at the given index.
   */
  double getCoordinates(int index);

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();
}