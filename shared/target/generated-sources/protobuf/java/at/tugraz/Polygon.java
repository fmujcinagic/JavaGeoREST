// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapservice.proto

package at.tugraz;

/**
 * Protobuf type {@code eventservice.Polygon}
 */
public final class Polygon extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:eventservice.Polygon)
    PolygonOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Polygon.newBuilder() to construct.
  private Polygon(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Polygon() {
    coordinates_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Polygon();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Polygon(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              coordinates_ = new java.util.ArrayList<at.tugraz.Coordinate>();
              mutable_bitField0_ |= 0x00000001;
            }
            coordinates_.add(
                input.readMessage(at.tugraz.Coordinate.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        coordinates_ = java.util.Collections.unmodifiableList(coordinates_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return at.tugraz.EventServiceProto.internal_static_eventservice_Polygon_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return at.tugraz.EventServiceProto.internal_static_eventservice_Polygon_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            at.tugraz.Polygon.class, at.tugraz.Polygon.Builder.class);
  }

  public static final int COORDINATES_FIELD_NUMBER = 1;
  private java.util.List<at.tugraz.Coordinate> coordinates_;
  /**
   * <pre>
   * A list of coordinates to represent a single polygon
   * </pre>
   *
   * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
   */
  @java.lang.Override
  public java.util.List<at.tugraz.Coordinate> getCoordinatesList() {
    return coordinates_;
  }
  /**
   * <pre>
   * A list of coordinates to represent a single polygon
   * </pre>
   *
   * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends at.tugraz.CoordinateOrBuilder> 
      getCoordinatesOrBuilderList() {
    return coordinates_;
  }
  /**
   * <pre>
   * A list of coordinates to represent a single polygon
   * </pre>
   *
   * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
   */
  @java.lang.Override
  public int getCoordinatesCount() {
    return coordinates_.size();
  }
  /**
   * <pre>
   * A list of coordinates to represent a single polygon
   * </pre>
   *
   * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
   */
  @java.lang.Override
  public at.tugraz.Coordinate getCoordinates(int index) {
    return coordinates_.get(index);
  }
  /**
   * <pre>
   * A list of coordinates to represent a single polygon
   * </pre>
   *
   * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
   */
  @java.lang.Override
  public at.tugraz.CoordinateOrBuilder getCoordinatesOrBuilder(
      int index) {
    return coordinates_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < coordinates_.size(); i++) {
      output.writeMessage(1, coordinates_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < coordinates_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, coordinates_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof at.tugraz.Polygon)) {
      return super.equals(obj);
    }
    at.tugraz.Polygon other = (at.tugraz.Polygon) obj;

    if (!getCoordinatesList()
        .equals(other.getCoordinatesList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getCoordinatesCount() > 0) {
      hash = (37 * hash) + COORDINATES_FIELD_NUMBER;
      hash = (53 * hash) + getCoordinatesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static at.tugraz.Polygon parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static at.tugraz.Polygon parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static at.tugraz.Polygon parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static at.tugraz.Polygon parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static at.tugraz.Polygon parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static at.tugraz.Polygon parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static at.tugraz.Polygon parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static at.tugraz.Polygon parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static at.tugraz.Polygon parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static at.tugraz.Polygon parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static at.tugraz.Polygon parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static at.tugraz.Polygon parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(at.tugraz.Polygon prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code eventservice.Polygon}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:eventservice.Polygon)
      at.tugraz.PolygonOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return at.tugraz.EventServiceProto.internal_static_eventservice_Polygon_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return at.tugraz.EventServiceProto.internal_static_eventservice_Polygon_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              at.tugraz.Polygon.class, at.tugraz.Polygon.Builder.class);
    }

    // Construct using at.tugraz.Polygon.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getCoordinatesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (coordinatesBuilder_ == null) {
        coordinates_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        coordinatesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return at.tugraz.EventServiceProto.internal_static_eventservice_Polygon_descriptor;
    }

    @java.lang.Override
    public at.tugraz.Polygon getDefaultInstanceForType() {
      return at.tugraz.Polygon.getDefaultInstance();
    }

    @java.lang.Override
    public at.tugraz.Polygon build() {
      at.tugraz.Polygon result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public at.tugraz.Polygon buildPartial() {
      at.tugraz.Polygon result = new at.tugraz.Polygon(this);
      int from_bitField0_ = bitField0_;
      if (coordinatesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          coordinates_ = java.util.Collections.unmodifiableList(coordinates_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.coordinates_ = coordinates_;
      } else {
        result.coordinates_ = coordinatesBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof at.tugraz.Polygon) {
        return mergeFrom((at.tugraz.Polygon)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(at.tugraz.Polygon other) {
      if (other == at.tugraz.Polygon.getDefaultInstance()) return this;
      if (coordinatesBuilder_ == null) {
        if (!other.coordinates_.isEmpty()) {
          if (coordinates_.isEmpty()) {
            coordinates_ = other.coordinates_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureCoordinatesIsMutable();
            coordinates_.addAll(other.coordinates_);
          }
          onChanged();
        }
      } else {
        if (!other.coordinates_.isEmpty()) {
          if (coordinatesBuilder_.isEmpty()) {
            coordinatesBuilder_.dispose();
            coordinatesBuilder_ = null;
            coordinates_ = other.coordinates_;
            bitField0_ = (bitField0_ & ~0x00000001);
            coordinatesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCoordinatesFieldBuilder() : null;
          } else {
            coordinatesBuilder_.addAllMessages(other.coordinates_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      at.tugraz.Polygon parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (at.tugraz.Polygon) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<at.tugraz.Coordinate> coordinates_ =
      java.util.Collections.emptyList();
    private void ensureCoordinatesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        coordinates_ = new java.util.ArrayList<at.tugraz.Coordinate>(coordinates_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        at.tugraz.Coordinate, at.tugraz.Coordinate.Builder, at.tugraz.CoordinateOrBuilder> coordinatesBuilder_;

    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public java.util.List<at.tugraz.Coordinate> getCoordinatesList() {
      if (coordinatesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(coordinates_);
      } else {
        return coordinatesBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public int getCoordinatesCount() {
      if (coordinatesBuilder_ == null) {
        return coordinates_.size();
      } else {
        return coordinatesBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public at.tugraz.Coordinate getCoordinates(int index) {
      if (coordinatesBuilder_ == null) {
        return coordinates_.get(index);
      } else {
        return coordinatesBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder setCoordinates(
        int index, at.tugraz.Coordinate value) {
      if (coordinatesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCoordinatesIsMutable();
        coordinates_.set(index, value);
        onChanged();
      } else {
        coordinatesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder setCoordinates(
        int index, at.tugraz.Coordinate.Builder builderForValue) {
      if (coordinatesBuilder_ == null) {
        ensureCoordinatesIsMutable();
        coordinates_.set(index, builderForValue.build());
        onChanged();
      } else {
        coordinatesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder addCoordinates(at.tugraz.Coordinate value) {
      if (coordinatesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCoordinatesIsMutable();
        coordinates_.add(value);
        onChanged();
      } else {
        coordinatesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder addCoordinates(
        int index, at.tugraz.Coordinate value) {
      if (coordinatesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCoordinatesIsMutable();
        coordinates_.add(index, value);
        onChanged();
      } else {
        coordinatesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder addCoordinates(
        at.tugraz.Coordinate.Builder builderForValue) {
      if (coordinatesBuilder_ == null) {
        ensureCoordinatesIsMutable();
        coordinates_.add(builderForValue.build());
        onChanged();
      } else {
        coordinatesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder addCoordinates(
        int index, at.tugraz.Coordinate.Builder builderForValue) {
      if (coordinatesBuilder_ == null) {
        ensureCoordinatesIsMutable();
        coordinates_.add(index, builderForValue.build());
        onChanged();
      } else {
        coordinatesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder addAllCoordinates(
        java.lang.Iterable<? extends at.tugraz.Coordinate> values) {
      if (coordinatesBuilder_ == null) {
        ensureCoordinatesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, coordinates_);
        onChanged();
      } else {
        coordinatesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder clearCoordinates() {
      if (coordinatesBuilder_ == null) {
        coordinates_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        coordinatesBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public Builder removeCoordinates(int index) {
      if (coordinatesBuilder_ == null) {
        ensureCoordinatesIsMutable();
        coordinates_.remove(index);
        onChanged();
      } else {
        coordinatesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public at.tugraz.Coordinate.Builder getCoordinatesBuilder(
        int index) {
      return getCoordinatesFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public at.tugraz.CoordinateOrBuilder getCoordinatesOrBuilder(
        int index) {
      if (coordinatesBuilder_ == null) {
        return coordinates_.get(index);  } else {
        return coordinatesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public java.util.List<? extends at.tugraz.CoordinateOrBuilder> 
         getCoordinatesOrBuilderList() {
      if (coordinatesBuilder_ != null) {
        return coordinatesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(coordinates_);
      }
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public at.tugraz.Coordinate.Builder addCoordinatesBuilder() {
      return getCoordinatesFieldBuilder().addBuilder(
          at.tugraz.Coordinate.getDefaultInstance());
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public at.tugraz.Coordinate.Builder addCoordinatesBuilder(
        int index) {
      return getCoordinatesFieldBuilder().addBuilder(
          index, at.tugraz.Coordinate.getDefaultInstance());
    }
    /**
     * <pre>
     * A list of coordinates to represent a single polygon
     * </pre>
     *
     * <code>repeated .eventservice.Coordinate coordinates = 1;</code>
     */
    public java.util.List<at.tugraz.Coordinate.Builder> 
         getCoordinatesBuilderList() {
      return getCoordinatesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        at.tugraz.Coordinate, at.tugraz.Coordinate.Builder, at.tugraz.CoordinateOrBuilder> 
        getCoordinatesFieldBuilder() {
      if (coordinatesBuilder_ == null) {
        coordinatesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            at.tugraz.Coordinate, at.tugraz.Coordinate.Builder, at.tugraz.CoordinateOrBuilder>(
                coordinates_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        coordinates_ = null;
      }
      return coordinatesBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:eventservice.Polygon)
  }

  // @@protoc_insertion_point(class_scope:eventservice.Polygon)
  private static final at.tugraz.Polygon DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new at.tugraz.Polygon();
  }

  public static at.tugraz.Polygon getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Polygon>
      PARSER = new com.google.protobuf.AbstractParser<Polygon>() {
    @java.lang.Override
    public Polygon parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Polygon(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Polygon> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Polygon> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public at.tugraz.Polygon getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
