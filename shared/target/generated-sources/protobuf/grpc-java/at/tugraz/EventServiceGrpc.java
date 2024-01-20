package at.tugraz;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.1)",
    comments = "Source: mapservice.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EventServiceGrpc {

  private EventServiceGrpc() {}

  public static final String SERVICE_NAME = "eventservice.EventService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      at.tugraz.Events> getEventsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "events",
      requestType = com.google.protobuf.Empty.class,
      responseType = at.tugraz.Events.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      at.tugraz.Events> getEventsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, at.tugraz.Events> getEventsMethod;
    if ((getEventsMethod = EventServiceGrpc.getEventsMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getEventsMethod = EventServiceGrpc.getEventsMethod) == null) {
          EventServiceGrpc.getEventsMethod = getEventsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, at.tugraz.Events>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "events"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Events.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("events"))
              .build();
        }
      }
    }
    return getEventsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.EventById,
      at.tugraz.Event> getEventByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "eventById",
      requestType = at.tugraz.EventById.class,
      responseType = at.tugraz.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.EventById,
      at.tugraz.Event> getEventByIdMethod() {
    io.grpc.MethodDescriptor<at.tugraz.EventById, at.tugraz.Event> getEventByIdMethod;
    if ((getEventByIdMethod = EventServiceGrpc.getEventByIdMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getEventByIdMethod = EventServiceGrpc.getEventByIdMethod) == null) {
          EventServiceGrpc.getEventByIdMethod = getEventByIdMethod =
              io.grpc.MethodDescriptor.<at.tugraz.EventById, at.tugraz.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "eventById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.EventById.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("eventById"))
              .build();
        }
      }
    }
    return getEventByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.EventCreation,
      at.tugraz.Event> getLogEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logEvent",
      requestType = at.tugraz.EventCreation.class,
      responseType = at.tugraz.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.EventCreation,
      at.tugraz.Event> getLogEventMethod() {
    io.grpc.MethodDescriptor<at.tugraz.EventCreation, at.tugraz.Event> getLogEventMethod;
    if ((getLogEventMethod = EventServiceGrpc.getLogEventMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getLogEventMethod = EventServiceGrpc.getLogEventMethod) == null) {
          EventServiceGrpc.getLogEventMethod = getLogEventMethod =
              io.grpc.MethodDescriptor.<at.tugraz.EventCreation, at.tugraz.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "logEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.EventCreation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("logEvent"))
              .build();
        }
      }
    }
    return getLogEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.OSMFileRequest,
      at.tugraz.OSMFileResponse> getGetOSMFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOSMFile",
      requestType = at.tugraz.OSMFileRequest.class,
      responseType = at.tugraz.OSMFileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.OSMFileRequest,
      at.tugraz.OSMFileResponse> getGetOSMFileMethod() {
    io.grpc.MethodDescriptor<at.tugraz.OSMFileRequest, at.tugraz.OSMFileResponse> getGetOSMFileMethod;
    if ((getGetOSMFileMethod = EventServiceGrpc.getGetOSMFileMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetOSMFileMethod = EventServiceGrpc.getGetOSMFileMethod) == null) {
          EventServiceGrpc.getGetOSMFileMethod = getGetOSMFileMethod =
              io.grpc.MethodDescriptor.<at.tugraz.OSMFileRequest, at.tugraz.OSMFileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOSMFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.OSMFileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.OSMFileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("getOSMFile"))
              .build();
        }
      }
    }
    return getGetOSMFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.RequestType,
      at.tugraz.ResponseType> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetData",
      requestType = at.tugraz.RequestType.class,
      responseType = at.tugraz.ResponseType.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.RequestType,
      at.tugraz.ResponseType> getGetDataMethod() {
    io.grpc.MethodDescriptor<at.tugraz.RequestType, at.tugraz.ResponseType> getGetDataMethod;
    if ((getGetDataMethod = EventServiceGrpc.getGetDataMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetDataMethod = EventServiceGrpc.getGetDataMethod) == null) {
          EventServiceGrpc.getGetDataMethod = getGetDataMethod =
              io.grpc.MethodDescriptor.<at.tugraz.RequestType, at.tugraz.ResponseType>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.RequestType.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.ResponseType.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("GetData"))
              .build();
        }
      }
    }
    return getGetDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.RoadById,
      at.tugraz.Event> getRoadByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "roadById",
      requestType = at.tugraz.RoadById.class,
      responseType = at.tugraz.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.RoadById,
      at.tugraz.Event> getRoadByIdMethod() {
    io.grpc.MethodDescriptor<at.tugraz.RoadById, at.tugraz.Event> getRoadByIdMethod;
    if ((getRoadByIdMethod = EventServiceGrpc.getRoadByIdMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getRoadByIdMethod = EventServiceGrpc.getRoadByIdMethod) == null) {
          EventServiceGrpc.getRoadByIdMethod = getRoadByIdMethod =
              io.grpc.MethodDescriptor.<at.tugraz.RoadById, at.tugraz.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "roadById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.RoadById.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("roadById"))
              .build();
        }
      }
    }
    return getRoadByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.Roads,
      at.tugraz.Event> getGetRoadsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRoads",
      requestType = at.tugraz.Roads.class,
      responseType = at.tugraz.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.Roads,
      at.tugraz.Event> getGetRoadsMethod() {
    io.grpc.MethodDescriptor<at.tugraz.Roads, at.tugraz.Event> getGetRoadsMethod;
    if ((getGetRoadsMethod = EventServiceGrpc.getGetRoadsMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetRoadsMethod = EventServiceGrpc.getGetRoadsMethod) == null) {
          EventServiceGrpc.getGetRoadsMethod = getGetRoadsMethod =
              io.grpc.MethodDescriptor.<at.tugraz.Roads, at.tugraz.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRoads"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Roads.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("GetRoads"))
              .build();
        }
      }
    }
    return getGetRoadsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.Amenity,
      at.tugraz.Event> getGetAmenityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAmenity",
      requestType = at.tugraz.Amenity.class,
      responseType = at.tugraz.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.Amenity,
      at.tugraz.Event> getGetAmenityMethod() {
    io.grpc.MethodDescriptor<at.tugraz.Amenity, at.tugraz.Event> getGetAmenityMethod;
    if ((getGetAmenityMethod = EventServiceGrpc.getGetAmenityMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetAmenityMethod = EventServiceGrpc.getGetAmenityMethod) == null) {
          EventServiceGrpc.getGetAmenityMethod = getGetAmenityMethod =
              io.grpc.MethodDescriptor.<at.tugraz.Amenity, at.tugraz.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAmenity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Amenity.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("GetAmenity"))
              .build();
        }
      }
    }
    return getGetAmenityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<at.tugraz.MappingRequest,
      at.tugraz.Event> getGetMappingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMapping",
      requestType = at.tugraz.MappingRequest.class,
      responseType = at.tugraz.Event.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<at.tugraz.MappingRequest,
      at.tugraz.Event> getGetMappingMethod() {
    io.grpc.MethodDescriptor<at.tugraz.MappingRequest, at.tugraz.Event> getGetMappingMethod;
    if ((getGetMappingMethod = EventServiceGrpc.getGetMappingMethod) == null) {
      synchronized (EventServiceGrpc.class) {
        if ((getGetMappingMethod = EventServiceGrpc.getGetMappingMethod) == null) {
          EventServiceGrpc.getGetMappingMethod = getGetMappingMethod =
              io.grpc.MethodDescriptor.<at.tugraz.MappingRequest, at.tugraz.Event>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMapping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.MappingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  at.tugraz.Event.getDefaultInstance()))
              .setSchemaDescriptor(new EventServiceMethodDescriptorSupplier("GetMapping"))
              .build();
        }
      }
    }
    return getGetMappingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceStub>() {
        @java.lang.Override
        public EventServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceStub(channel, callOptions);
        }
      };
    return EventServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceBlockingStub>() {
        @java.lang.Override
        public EventServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceBlockingStub(channel, callOptions);
        }
      };
    return EventServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EventServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EventServiceFutureStub>() {
        @java.lang.Override
        public EventServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EventServiceFutureStub(channel, callOptions);
        }
      };
    return EventServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void events(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<at.tugraz.Events> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEventsMethod(), responseObserver);
    }

    /**
     */
    default void eventById(at.tugraz.EventById request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEventByIdMethod(), responseObserver);
    }

    /**
     */
    default void logEvent(at.tugraz.EventCreation request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLogEventMethod(), responseObserver);
    }

    /**
     */
    default void getOSMFile(at.tugraz.OSMFileRequest request,
        io.grpc.stub.StreamObserver<at.tugraz.OSMFileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOSMFileMethod(), responseObserver);
    }

    /**
     */
    default void getData(at.tugraz.RequestType request,
        io.grpc.stub.StreamObserver<at.tugraz.ResponseType> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }

    /**
     * <pre>
     *for the roads
     * </pre>
     */
    default void roadById(at.tugraz.RoadById request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRoadByIdMethod(), responseObserver);
    }

    /**
     */
    default void getRoads(at.tugraz.Roads request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRoadsMethod(), responseObserver);
    }

    /**
     * <pre>
     *amenity full message
     * </pre>
     */
    default void getAmenity(at.tugraz.Amenity request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAmenityMethod(), responseObserver);
    }

    /**
     * <pre>
     * this is for mapping
     * </pre>
     */
    default void getMapping(at.tugraz.MappingRequest request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMappingMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service EventService.
   */
  public static abstract class EventServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return EventServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service EventService.
   */
  public static final class EventServiceStub
      extends io.grpc.stub.AbstractAsyncStub<EventServiceStub> {
    private EventServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceStub(channel, callOptions);
    }

    /**
     */
    public void events(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<at.tugraz.Events> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEventsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void eventById(at.tugraz.EventById request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEventByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logEvent(at.tugraz.EventCreation request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLogEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOSMFile(at.tugraz.OSMFileRequest request,
        io.grpc.stub.StreamObserver<at.tugraz.OSMFileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOSMFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getData(at.tugraz.RequestType request,
        io.grpc.stub.StreamObserver<at.tugraz.ResponseType> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *for the roads
     * </pre>
     */
    public void roadById(at.tugraz.RoadById request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRoadByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getRoads(at.tugraz.Roads request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetRoadsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *amenity full message
     * </pre>
     */
    public void getAmenity(at.tugraz.Amenity request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAmenityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * this is for mapping
     * </pre>
     */
    public void getMapping(at.tugraz.MappingRequest request,
        io.grpc.stub.StreamObserver<at.tugraz.Event> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMappingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service EventService.
   */
  public static final class EventServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<EventServiceBlockingStub> {
    private EventServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public at.tugraz.Events events(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEventsMethod(), getCallOptions(), request);
    }

    /**
     */
    public at.tugraz.Event eventById(at.tugraz.EventById request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEventByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public at.tugraz.Event logEvent(at.tugraz.EventCreation request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLogEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public at.tugraz.OSMFileResponse getOSMFile(at.tugraz.OSMFileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOSMFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public at.tugraz.ResponseType getData(at.tugraz.RequestType request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *for the roads
     * </pre>
     */
    public at.tugraz.Event roadById(at.tugraz.RoadById request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRoadByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public at.tugraz.Event getRoads(at.tugraz.Roads request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetRoadsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *amenity full message
     * </pre>
     */
    public at.tugraz.Event getAmenity(at.tugraz.Amenity request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAmenityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * this is for mapping
     * </pre>
     */
    public at.tugraz.Event getMapping(at.tugraz.MappingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMappingMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service EventService.
   */
  public static final class EventServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<EventServiceFutureStub> {
    private EventServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EventServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Events> events(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEventsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Event> eventById(
        at.tugraz.EventById request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEventByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Event> logEvent(
        at.tugraz.EventCreation request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLogEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.OSMFileResponse> getOSMFile(
        at.tugraz.OSMFileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOSMFileMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.ResponseType> getData(
        at.tugraz.RequestType request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *for the roads
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Event> roadById(
        at.tugraz.RoadById request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRoadByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Event> getRoads(
        at.tugraz.Roads request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetRoadsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *amenity full message
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Event> getAmenity(
        at.tugraz.Amenity request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAmenityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * this is for mapping
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<at.tugraz.Event> getMapping(
        at.tugraz.MappingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMappingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EVENTS = 0;
  private static final int METHODID_EVENT_BY_ID = 1;
  private static final int METHODID_LOG_EVENT = 2;
  private static final int METHODID_GET_OSMFILE = 3;
  private static final int METHODID_GET_DATA = 4;
  private static final int METHODID_ROAD_BY_ID = 5;
  private static final int METHODID_GET_ROADS = 6;
  private static final int METHODID_GET_AMENITY = 7;
  private static final int METHODID_GET_MAPPING = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EVENTS:
          serviceImpl.events((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Events>) responseObserver);
          break;
        case METHODID_EVENT_BY_ID:
          serviceImpl.eventById((at.tugraz.EventById) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Event>) responseObserver);
          break;
        case METHODID_LOG_EVENT:
          serviceImpl.logEvent((at.tugraz.EventCreation) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Event>) responseObserver);
          break;
        case METHODID_GET_OSMFILE:
          serviceImpl.getOSMFile((at.tugraz.OSMFileRequest) request,
              (io.grpc.stub.StreamObserver<at.tugraz.OSMFileResponse>) responseObserver);
          break;
        case METHODID_GET_DATA:
          serviceImpl.getData((at.tugraz.RequestType) request,
              (io.grpc.stub.StreamObserver<at.tugraz.ResponseType>) responseObserver);
          break;
        case METHODID_ROAD_BY_ID:
          serviceImpl.roadById((at.tugraz.RoadById) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Event>) responseObserver);
          break;
        case METHODID_GET_ROADS:
          serviceImpl.getRoads((at.tugraz.Roads) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Event>) responseObserver);
          break;
        case METHODID_GET_AMENITY:
          serviceImpl.getAmenity((at.tugraz.Amenity) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Event>) responseObserver);
          break;
        case METHODID_GET_MAPPING:
          serviceImpl.getMapping((at.tugraz.MappingRequest) request,
              (io.grpc.stub.StreamObserver<at.tugraz.Event>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getEventsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              at.tugraz.Events>(
                service, METHODID_EVENTS)))
        .addMethod(
          getEventByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.EventById,
              at.tugraz.Event>(
                service, METHODID_EVENT_BY_ID)))
        .addMethod(
          getLogEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.EventCreation,
              at.tugraz.Event>(
                service, METHODID_LOG_EVENT)))
        .addMethod(
          getGetOSMFileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.OSMFileRequest,
              at.tugraz.OSMFileResponse>(
                service, METHODID_GET_OSMFILE)))
        .addMethod(
          getGetDataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.RequestType,
              at.tugraz.ResponseType>(
                service, METHODID_GET_DATA)))
        .addMethod(
          getRoadByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.RoadById,
              at.tugraz.Event>(
                service, METHODID_ROAD_BY_ID)))
        .addMethod(
          getGetRoadsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.Roads,
              at.tugraz.Event>(
                service, METHODID_GET_ROADS)))
        .addMethod(
          getGetAmenityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.Amenity,
              at.tugraz.Event>(
                service, METHODID_GET_AMENITY)))
        .addMethod(
          getGetMappingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              at.tugraz.MappingRequest,
              at.tugraz.Event>(
                service, METHODID_GET_MAPPING)))
        .build();
  }

  private static abstract class EventServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return at.tugraz.EventServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventService");
    }
  }

  private static final class EventServiceFileDescriptorSupplier
      extends EventServiceBaseDescriptorSupplier {
    EventServiceFileDescriptorSupplier() {}
  }

  private static final class EventServiceMethodDescriptorSupplier
      extends EventServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EventServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EventServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventServiceFileDescriptorSupplier())
              .addMethod(getEventsMethod())
              .addMethod(getEventByIdMethod())
              .addMethod(getLogEventMethod())
              .addMethod(getGetOSMFileMethod())
              .addMethod(getGetDataMethod())
              .addMethod(getRoadByIdMethod())
              .addMethod(getGetRoadsMethod())
              .addMethod(getGetAmenityMethod())
              .addMethod(getGetMappingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
