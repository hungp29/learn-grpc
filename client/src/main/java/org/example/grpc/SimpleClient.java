package org.example.grpc;

import com.example.grpc.AccountServiceGrpc;
import com.example.grpc.AccountInfoRequest;
import com.example.grpc.AccountInfoResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SimpleClient extends Client {

    private final AccountServiceGrpc.AccountServiceBlockingStub blockingStub;

    public SimpleClient() {
        super();
        blockingStub = AccountServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void handle() {
        AccountInfoRequest request = AccountInfoRequest.newBuilder().setId(1).build();
        AccountInfoResponse response = blockingStub.getAccountInfo(request);
        System.out.printf("%d - %s %s%n", response.getId(), response.getFirstName(), response.getLastName());
    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleClient().run();
    }
}
