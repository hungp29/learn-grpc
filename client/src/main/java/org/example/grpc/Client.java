package org.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

public abstract class Client {
    protected ManagedChannel channel;

    public Client() {
        channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }

    public abstract void handle();

    public void run() throws InterruptedException {
        try {
            handle();
        } finally {
            channel.awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
