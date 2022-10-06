# learn-grpc
### gRPC with Java

### Introduction
- gRPC is based around the idea of defining a service, specifying the methods that can be called remotely with their parameters and return types.
- gRPC uses protocol buffers as the Interface Definition Language (IDL) for describing both the service interface and the structure of the payload messages.

***Example:***

```
service HelloService {
    rpc SayHello (HelloRequest) returns (HelloResponse);
}
message HelloRequest {
    string greeting = 1;
}
message HelloResponse {
    string reply = 1;
}
```

### Kind of service method
- **Unary RPCs:** client sends a single request to the server and gets a single response back, just like a normal function call.
- **Server streaming RPCs:** the client sends a request to the server and gets a stream to read a sequence of messages back.
- **Client streaming RPCs:** the client writes a sequence of messages and sends them to the server. Once the client has finished writing the messages, it waits for the server to read them and return its response.
- **Bidirectional streaming RPCs:** both sides send a sequence of messages using a read-write stream.

### Others
- **Deadlines/Timeouts:** gRPC allows clients to specify how long they are willing to wait for an RPC to complete before the RPC is terminated with a DEADLINE_EXCEEDED error. On the server side, the server can query to see if a particular RPC has timed out, or how much time is left to complete the RPC.
- **RPC termination:** In gRPC, both the client and server make independent and local determinations of the success of the call, and their conclusions may not match.
- **Channels:** A gRPC channel provides a connection to a gRPC server on a specified host and port. It is used when creating a client stub.

### Implementation
1. Starting from a service definition in a .proto file.
2. Using protocol buffer compiler plugins to generate client- and server-side code.
3. On the server side, the server implements the methods declared by the service and runs a gRPC server to handle client calls.
4. On the client side, the client has a local object known as stub that implements the same methods as the service, the client can then just call those methods on the local object.

### Note
- In Java, when you define a message with optional fields, they still throw a `NullPointerException` if you set a `null` value for these fields.

### Other RPCs
- [Thrift](https://thrift.apache.org/)
- [Kryo](https://github.com/EsotericSoftware/kryo)
