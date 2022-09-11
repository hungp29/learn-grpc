package org.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.grpc.bidirectionalstream.SalaryServiceGrpc;
import org.example.grpc.clientsidestream.ProductServiceGrpc;
import org.example.grpc.serversidestream.DepartmentServiceGrpc;
import org.example.grpc.simple.AccountServiceGrpc;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new AccountServiceGrpc()) // Simple RPC
                .addService(new DepartmentServiceGrpc()) // Server side streaming RPC
                .addService(new ProductServiceGrpc()) // Client side streaming RPC
                .addService(new SalaryServiceGrpc()) // Bidirectional stream RPC
                .build();

        server.start();
        System.out.println("[Server] gRPC server started");
        server.awaitTermination();
    }
}
