// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapservice.proto

package at.tugraz;

public interface GeomOrBuilder extends
    // @@protoc_insertion_point(interface_extends:eventservice.Geom)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string type = 1;</code>
   * @return The type.
   */
  java.lang.String getType();
  /**
   * <code>string type = 1;</code>
   * @return The bytes for type.
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>repeated double coordinates = 2;</code>
   * @return A list containing the coordinates.
   */
  java.util.List<java.lang.Double> getCoordinatesList();
  /**
   * <code>repeated double coordinates = 2;</code>
   * @return The count of coordinates.
   */
  int getCoordinatesCount();
  /**
   * <code>repeated double coordinates = 2;</code>
   * @param index The index of the element to return.
   * @return The coordinates at the given index.
   */
  double getCoordinates(int index);

  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  java.util.List<at.tugraz.MultiPolygon> 
      getMultiPolygonsList();
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  at.tugraz.MultiPolygon getMultiPolygons(int index);
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  int getMultiPolygonsCount();
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  java.util.List<? extends at.tugraz.MultiPolygonOrBuilder> 
      getMultiPolygonsOrBuilderList();
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  at.tugraz.MultiPolygonOrBuilder getMultiPolygonsOrBuilder(
      int index);

  /**
   * <code>.eventservice.Crs crs = 4;</code>
   * @return Whether the crs field is set.
   */
  boolean hasCrs();
  /**
   * <code>.eventservice.Crs crs = 4;</code>
   * @return The crs.
   */
  at.tugraz.Crs getCrs();
  /**
   * <code>.eventservice.Crs crs = 4;</code>
   */
  at.tugraz.CrsOrBuilder getCrsOrBuilder();
}
