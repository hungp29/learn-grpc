package org.example.grpc;

import com.example.grpc.AccountInfoRequest;
import com.example.grpc.NumOfProductResponse;
import com.example.grpc.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class ClientSideStreamingClient extends Client {

    private final ProductServiceGrpc.ProductServiceStub nonBlockingStub;

    public ClientSideStreamingClient() {
        super();
        nonBlockingStub = ProductServiceGrpc.newStub(channel);
    }

    private StreamObserver<NumOfProductResponse> responseStreamObserver() {
        return new StreamObserver<>() {
            @Override
            public void onNext(NumOfProductResponse numOfProductResponse) {
                System.out.printf("[Client][ClientSideStreaming] Number of product: %d%n", numOfProductResponse.getNumOfProduct());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Client][ClientSideStreaming] Error " + throwable.getMessage());
                // TODO: handling error
            }

            @Override
            public void onCompleted() {
                System.out.println("[Client][ClientSideStreaming] Finished");
            }
        };
    }

    @Override
    public void handle() {
        StreamObserver<AccountInfoRequest> requestStreamObserver = nonBlockingStub.countProduct(responseStreamObserver());
        for (int i = 1; i < 3; i++) {
            AccountInfoRequest accountInfoRequest = AccountInfoRequest.newBuilder()
                    .setId(i)
                    .build();
            requestStreamObserver.onNext(accountInfoRequest);
        }
        requestStreamObserver.onCompleted();
    }

    public static void main(String[] args) throws InterruptedException {
        new ClientSideStreamingClient().run();
    }
}
