// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapservice.proto

package at.tugraz;

/**
 * Protobuf type {@code eventservice.Geom}
 */
public final class Geom extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:eventservice.Geom)
    GeomOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Geom.newBuilder() to construct.
  private Geom(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Geom() {
    type_ = "";
    coordinates_ = emptyDoubleList();
    multiPolygons_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Geom();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Geom(
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
            java.lang.String s = input.readStringRequireUtf8();

            type_ = s;
            break;
          }
          case 17: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              coordinates_ = newDoubleList();
              mutable_bitField0_ |= 0x00000001;
            }
            coordinates_.addDouble(input.readDouble());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              coordinates_ = newDoubleList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              coordinates_.addDouble(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              multiPolygons_ = new java.util.ArrayList<at.tugraz.MultiPolygon>();
              mutable_bitField0_ |= 0x00000002;
            }
            multiPolygons_.add(
                input.readMessage(at.tugraz.MultiPolygon.parser(), extensionRegistry));
            break;
          }
          case 34: {
            at.tugraz.Crs.Builder subBuilder = null;
            if (crs_ != null) {
              subBuilder = crs_.toBuilder();
            }
            crs_ = input.readMessage(at.tugraz.Crs.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(crs_);
              crs_ = subBuilder.buildPartial();
            }

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
        coordinates_.makeImmutable(); // C
      }
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        multiPolygons_ = java.util.Collections.unmodifiableList(multiPolygons_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return at.tugraz.EventServiceProto.internal_static_eventservice_Geom_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return at.tugraz.EventServiceProto.internal_static_eventservice_Geom_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            at.tugraz.Geom.class, at.tugraz.Geom.Builder.class);
  }

  public static final int TYPE_FIELD_NUMBER = 1;
  private volatile java.lang.Object type_;
  /**
   * <code>string type = 1;</code>
   * @return The type.
   */
  @java.lang.Override
  public java.lang.String getType() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <code>string type = 1;</code>
   * @return The bytes for type.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTypeBytes() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int COORDINATES_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.DoubleList coordinates_;
  /**
   * <code>repeated double coordinates = 2;</code>
   * @return A list containing the coordinates.
   */
  @java.lang.Override
  public java.util.List<java.lang.Double>
      getCoordinatesList() {
    return coordinates_;
  }
  /**
   * <code>repeated double coordinates = 2;</code>
   * @return The count of coordinates.
   */
  public int getCoordinatesCount() {
    return coordinates_.size();
  }
  /**
   * <code>repeated double coordinates = 2;</code>
   * @param index The index of the element to return.
   * @return The coordinates at the given index.
   */
  public double getCoordinates(int index) {
    return coordinates_.getDouble(index);
  }
  private int coordinatesMemoizedSerializedSize = -1;

  public static final int MULTIPOLYGONS_FIELD_NUMBER = 3;
  private java.util.List<at.tugraz.MultiPolygon> multiPolygons_;
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  @java.lang.Override
  public java.util.List<at.tugraz.MultiPolygon> getMultiPolygonsList() {
    return multiPolygons_;
  }
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends at.tugraz.MultiPolygonOrBuilder> 
      getMultiPolygonsOrBuilderList() {
    return multiPolygons_;
  }
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  @java.lang.Override
  public int getMultiPolygonsCount() {
    return multiPolygons_.size();
  }
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  @java.lang.Override
  public at.tugraz.MultiPolygon getMultiPolygons(int index) {
    return multiPolygons_.get(index);
  }
  /**
   * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
   */
  @java.lang.Override
  public at.tugraz.MultiPolygonOrBuilder getMultiPolygonsOrBuilder(
      int index) {
    return multiPolygons_.get(index);
  }

  public static final int CRS_FIELD_NUMBER = 4;
  private at.tugraz.Crs crs_;
  /**
   * <code>.eventservice.Crs crs = 4;</code>
   * @return Whether the crs field is set.
   */
  @java.lang.Override
  public boolean hasCrs() {
    return crs_ != null;
  }
  /**
   * <code>.eventservice.Crs crs = 4;</code>
   * @return The crs.
   */
  @java.lang.Override
  public at.tugraz.Crs getCrs() {
    return crs_ == null ? at.tugraz.Crs.getDefaultInstance() : crs_;
  }
  /**
   * <code>.eventservice.Crs crs = 4;</code>
   */
  @java.lang.Override
  public at.tugraz.CrsOrBuilder getCrsOrBuilder() {
    return getCrs();
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
    getSerializedSize();
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, type_);
    }
    if (getCoordinatesList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(coordinatesMemoizedSerializedSize);
    }
    for (int i = 0; i < coordinates_.size(); i++) {
      output.writeDoubleNoTag(coordinates_.getDouble(i));
    }
    for (int i = 0; i < multiPolygons_.size(); i++) {
      output.writeMessage(3, multiPolygons_.get(i));
    }
    if (crs_ != null) {
      output.writeMessage(4, getCrs());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, type_);
    }
    {
      int dataSize = 0;
      dataSize = 8 * getCoordinatesList().size();
      size += dataSize;
      if (!getCoordinatesList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      coordinatesMemoizedSerializedSize = dataSize;
    }
    for (int i = 0; i < multiPolygons_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, multiPolygons_.get(i));
    }
    if (crs_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getCrs());
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
    if (!(obj instanceof at.tugraz.Geom)) {
      return super.equals(obj);
    }
    at.tugraz.Geom other = (at.tugraz.Geom) obj;

    if (!getType()
        .equals(other.getType())) return false;
    if (!getCoordinatesList()
        .equals(other.getCoordinatesList())) return false;
    if (!getMultiPolygonsList()
        .equals(other.getMultiPolygonsList())) return false;
    if (hasCrs() != other.hasCrs()) return false;
    if (hasCrs()) {
      if (!getCrs()
          .equals(other.getCrs())) return false;
    }
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
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    if (getCoordinatesCount() > 0) {
      hash = (37 * hash) + COORDINATES_FIELD_NUMBER;
      hash = (53 * hash) + getCoordinatesList().hashCode();
    }
    if (getMultiPolygonsCount() > 0) {
      hash = (37 * hash) + MULTIPOLYGONS_FIELD_NUMBER;
      hash = (53 * hash) + getMultiPolygonsList().hashCode();
    }
    if (hasCrs()) {
      hash = (37 * hash) + CRS_FIELD_NUMBER;
      hash = (53 * hash) + getCrs().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static at.tugraz.Geom parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static at.tugraz.Geom parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static at.tugraz.Geom parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static at.tugraz.Geom parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static at.tugraz.Geom parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static at.tugraz.Geom parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static at.tugraz.Geom parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static at.tugraz.Geom parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static at.tugraz.Geom parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static at.tugraz.Geom parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static at.tugraz.Geom parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static at.tugraz.Geom parseFrom(
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
  public static Builder newBuilder(at.tugraz.Geom prototype) {
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
   * Protobuf type {@code eventservice.Geom}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:eventservice.Geom)
      at.tugraz.GeomOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return at.tugraz.EventServiceProto.internal_static_eventservice_Geom_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return at.tugraz.EventServiceProto.internal_static_eventservice_Geom_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              at.tugraz.Geom.class, at.tugraz.Geom.Builder.class);
    }

    // Construct using at.tugraz.Geom.newBuilder()
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
        getMultiPolygonsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      type_ = "";

      coordinates_ = emptyDoubleList();
      bitField0_ = (bitField0_ & ~0x00000001);
      if (multiPolygonsBuilder_ == null) {
        multiPolygons_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        multiPolygonsBuilder_.clear();
      }
      if (crsBuilder_ == null) {
        crs_ = null;
      } else {
        crs_ = null;
        crsBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return at.tugraz.EventServiceProto.internal_static_eventservice_Geom_descriptor;
    }

    @java.lang.Override
    public at.tugraz.Geom getDefaultInstanceForType() {
      return at.tugraz.Geom.getDefaultInstance();
    }

    @java.lang.Override
    public at.tugraz.Geom build() {
      at.tugraz.Geom result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public at.tugraz.Geom buildPartial() {
      at.tugraz.Geom result = new at.tugraz.Geom(this);
      int from_bitField0_ = bitField0_;
      result.type_ = type_;
      if (((bitField0_ & 0x00000001) != 0)) {
        coordinates_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.coordinates_ = coordinates_;
      if (multiPolygonsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0)) {
          multiPolygons_ = java.util.Collections.unmodifiableList(multiPolygons_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.multiPolygons_ = multiPolygons_;
      } else {
        result.multiPolygons_ = multiPolygonsBuilder_.build();
      }
      if (crsBuilder_ == null) {
        result.crs_ = crs_;
      } else {
        result.crs_ = crsBuilder_.build();
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
      if (other instanceof at.tugraz.Geom) {
        return mergeFrom((at.tugraz.Geom)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(at.tugraz.Geom other) {
      if (other == at.tugraz.Geom.getDefaultInstance()) return this;
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
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
      if (multiPolygonsBuilder_ == null) {
        if (!other.multiPolygons_.isEmpty()) {
          if (multiPolygons_.isEmpty()) {
            multiPolygons_ = other.multiPolygons_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureMultiPolygonsIsMutable();
            multiPolygons_.addAll(other.multiPolygons_);
          }
          onChanged();
        }
      } else {
        if (!other.multiPolygons_.isEmpty()) {
          if (multiPolygonsBuilder_.isEmpty()) {
            multiPolygonsBuilder_.dispose();
            multiPolygonsBuilder_ = null;
            multiPolygons_ = other.multiPolygons_;
            bitField0_ = (bitField0_ & ~0x00000002);
            multiPolygonsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMultiPolygonsFieldBuilder() : null;
          } else {
            multiPolygonsBuilder_.addAllMessages(other.multiPolygons_);
          }
        }
      }
      if (other.hasCrs()) {
        mergeCrs(other.getCrs());
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
      at.tugraz.Geom parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (at.tugraz.Geom) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object type_ = "";
    /**
     * <code>string type = 1;</code>
     * @return The type.
     */
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string type = 1;</code>
     * @return The bytes for type.
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string type = 1;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string type = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <code>string type = 1;</code>
     * @param value The bytes for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.Internal.DoubleList coordinates_ = emptyDoubleList();
    private void ensureCoordinatesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        coordinates_ = mutableCopy(coordinates_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @return A list containing the coordinates.
     */
    public java.util.List<java.lang.Double>
        getCoordinatesList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(coordinates_) : coordinates_;
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @return The count of coordinates.
     */
    public int getCoordinatesCount() {
      return coordinates_.size();
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @param index The index of the element to return.
     * @return The coordinates at the given index.
     */
    public double getCoordinates(int index) {
      return coordinates_.getDouble(index);
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @param index The index to set the value at.
     * @param value The coordinates to set.
     * @return This builder for chaining.
     */
    public Builder setCoordinates(
        int index, double value) {
      ensureCoordinatesIsMutable();
      coordinates_.setDouble(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @param value The coordinates to add.
     * @return This builder for chaining.
     */
    public Builder addCoordinates(double value) {
      ensureCoordinatesIsMutable();
      coordinates_.addDouble(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @param values The coordinates to add.
     * @return This builder for chaining.
     */
    public Builder addAllCoordinates(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureCoordinatesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, coordinates_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated double coordinates = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCoordinates() {
      coordinates_ = emptyDoubleList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private java.util.List<at.tugraz.MultiPolygon> multiPolygons_ =
      java.util.Collections.emptyList();
    private void ensureMultiPolygonsIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        multiPolygons_ = new java.util.ArrayList<at.tugraz.MultiPolygon>(multiPolygons_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        at.tugraz.MultiPolygon, at.tugraz.MultiPolygon.Builder, at.tugraz.MultiPolygonOrBuilder> multiPolygonsBuilder_;

    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public java.util.List<at.tugraz.MultiPolygon> getMultiPolygonsList() {
      if (multiPolygonsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(multiPolygons_);
      } else {
        return multiPolygonsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public int getMultiPolygonsCount() {
      if (multiPolygonsBuilder_ == null) {
        return multiPolygons_.size();
      } else {
        return multiPolygonsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public at.tugraz.MultiPolygon getMultiPolygons(int index) {
      if (multiPolygonsBuilder_ == null) {
        return multiPolygons_.get(index);
      } else {
        return multiPolygonsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder setMultiPolygons(
        int index, at.tugraz.MultiPolygon value) {
      if (multiPolygonsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMultiPolygonsIsMutable();
        multiPolygons_.set(index, value);
        onChanged();
      } else {
        multiPolygonsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder setMultiPolygons(
        int index, at.tugraz.MultiPolygon.Builder builderForValue) {
      if (multiPolygonsBuilder_ == null) {
        ensureMultiPolygonsIsMutable();
        multiPolygons_.set(index, builderForValue.build());
        onChanged();
      } else {
        multiPolygonsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder addMultiPolygons(at.tugraz.MultiPolygon value) {
      if (multiPolygonsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMultiPolygonsIsMutable();
        multiPolygons_.add(value);
        onChanged();
      } else {
        multiPolygonsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder addMultiPolygons(
        int index, at.tugraz.MultiPolygon value) {
      if (multiPolygonsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMultiPolygonsIsMutable();
        multiPolygons_.add(index, value);
        onChanged();
      } else {
        multiPolygonsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder addMultiPolygons(
        at.tugraz.MultiPolygon.Builder builderForValue) {
      if (multiPolygonsBuilder_ == null) {
        ensureMultiPolygonsIsMutable();
        multiPolygons_.add(builderForValue.build());
        onChanged();
      } else {
        multiPolygonsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder addMultiPolygons(
        int index, at.tugraz.MultiPolygon.Builder builderForValue) {
      if (multiPolygonsBuilder_ == null) {
        ensureMultiPolygonsIsMutable();
        multiPolygons_.add(index, builderForValue.build());
        onChanged();
      } else {
        multiPolygonsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder addAllMultiPolygons(
        java.lang.Iterable<? extends at.tugraz.MultiPolygon> values) {
      if (multiPolygonsBuilder_ == null) {
        ensureMultiPolygonsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, multiPolygons_);
        onChanged();
      } else {
        multiPolygonsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder clearMultiPolygons() {
      if (multiPolygonsBuilder_ == null) {
        multiPolygons_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        multiPolygonsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public Builder removeMultiPolygons(int index) {
      if (multiPolygonsBuilder_ == null) {
        ensureMultiPolygonsIsMutable();
        multiPolygons_.remove(index);
        onChanged();
      } else {
        multiPolygonsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public at.tugraz.MultiPolygon.Builder getMultiPolygonsBuilder(
        int index) {
      return getMultiPolygonsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public at.tugraz.MultiPolygonOrBuilder getMultiPolygonsOrBuilder(
        int index) {
      if (multiPolygonsBuilder_ == null) {
        return multiPolygons_.get(index);  } else {
        return multiPolygonsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public java.util.List<? extends at.tugraz.MultiPolygonOrBuilder> 
         getMultiPolygonsOrBuilderList() {
      if (multiPolygonsBuilder_ != null) {
        return multiPolygonsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(multiPolygons_);
      }
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public at.tugraz.MultiPolygon.Builder addMultiPolygonsBuilder() {
      return getMultiPolygonsFieldBuilder().addBuilder(
          at.tugraz.MultiPolygon.getDefaultInstance());
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public at.tugraz.MultiPolygon.Builder addMultiPolygonsBuilder(
        int index) {
      return getMultiPolygonsFieldBuilder().addBuilder(
          index, at.tugraz.MultiPolygon.getDefaultInstance());
    }
    /**
     * <code>repeated .eventservice.MultiPolygon multiPolygons = 3;</code>
     */
    public java.util.List<at.tugraz.MultiPolygon.Builder> 
         getMultiPolygonsBuilderList() {
      return getMultiPolygonsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        at.tugraz.MultiPolygon, at.tugraz.MultiPolygon.Builder, at.tugraz.MultiPolygonOrBuilder> 
        getMultiPolygonsFieldBuilder() {
      if (multiPolygonsBuilder_ == null) {
        multiPolygonsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            at.tugraz.MultiPolygon, at.tugraz.MultiPolygon.Builder, at.tugraz.MultiPolygonOrBuilder>(
                multiPolygons_,
                ((bitField0_ & 0x00000002) != 0),
                getParentForChildren(),
                isClean());
        multiPolygons_ = null;
      }
      return multiPolygonsBuilder_;
    }

    private at.tugraz.Crs crs_;
    private com.google.protobuf.SingleFieldBuilderV3<
        at.tugraz.Crs, at.tugraz.Crs.Builder, at.tugraz.CrsOrBuilder> crsBuilder_;
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     * @return Whether the crs field is set.
     */
    public boolean hasCrs() {
      return crsBuilder_ != null || crs_ != null;
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     * @return The crs.
     */
    public at.tugraz.Crs getCrs() {
      if (crsBuilder_ == null) {
        return crs_ == null ? at.tugraz.Crs.getDefaultInstance() : crs_;
      } else {
        return crsBuilder_.getMessage();
      }
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    public Builder setCrs(at.tugraz.Crs value) {
      if (crsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        crs_ = value;
        onChanged();
      } else {
        crsBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    public Builder setCrs(
        at.tugraz.Crs.Builder builderForValue) {
      if (crsBuilder_ == null) {
        crs_ = builderForValue.build();
        onChanged();
      } else {
        crsBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    public Builder mergeCrs(at.tugraz.Crs value) {
      if (crsBuilder_ == null) {
        if (crs_ != null) {
          crs_ =
            at.tugraz.Crs.newBuilder(crs_).mergeFrom(value).buildPartial();
        } else {
          crs_ = value;
        }
        onChanged();
      } else {
        crsBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    public Builder clearCrs() {
      if (crsBuilder_ == null) {
        crs_ = null;
        onChanged();
      } else {
        crs_ = null;
        crsBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    public at.tugraz.Crs.Builder getCrsBuilder() {
      
      onChanged();
      return getCrsFieldBuilder().getBuilder();
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    public at.tugraz.CrsOrBuilder getCrsOrBuilder() {
      if (crsBuilder_ != null) {
        return crsBuilder_.getMessageOrBuilder();
      } else {
        return crs_ == null ?
            at.tugraz.Crs.getDefaultInstance() : crs_;
      }
    }
    /**
     * <code>.eventservice.Crs crs = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        at.tugraz.Crs, at.tugraz.Crs.Builder, at.tugraz.CrsOrBuilder> 
        getCrsFieldBuilder() {
      if (crsBuilder_ == null) {
        crsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            at.tugraz.Crs, at.tugraz.Crs.Builder, at.tugraz.CrsOrBuilder>(
                getCrs(),
                getParentForChildren(),
                isClean());
        crs_ = null;
      }
      return crsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:eventservice.Geom)
  }

  // @@protoc_insertion_point(class_scope:eventservice.Geom)
  private static final at.tugraz.Geom DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new at.tugraz.Geom();
  }

  public static at.tugraz.Geom getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Geom>
      PARSER = new com.google.protobuf.AbstractParser<Geom>() {
    @java.lang.Override
    public Geom parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Geom(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Geom> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Geom> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public at.tugraz.Geom getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

