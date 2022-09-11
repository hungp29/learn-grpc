package org.example.grpc;

import com.example.grpc.AccountInfoRequest;
import com.example.grpc.SalaryResponse;
import com.example.grpc.SalaryServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BidirectionalStreamingClient extends Client {

    private final SalaryServiceGrpc.SalaryServiceStub nonBlockingStub;

    public BidirectionalStreamingClient() {
        super();
        nonBlockingStub = SalaryServiceGrpc.newStub(channel);
    }

    private StreamObserver<SalaryResponse> responseStreamObserver() {
        return new StreamObserver<>() {
            @Override
            public void onNext(SalaryResponse salaryResponse) {
                System.out.printf("[Client][BidirectionalStreaming] %d - %s has salary: %d%n",
                        salaryResponse.getAccountId(),
                        salaryResponse.getAccountName(),
                        salaryResponse.getSalary());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Client][BidirectionalStreaming] Error " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("[Client][BidirectionalStreaming] Finished");
            }
        };
    }

    @Override
    public void handle() {
        StreamObserver<AccountInfoRequest> requestStreamObserver = nonBlockingStub.getSalary(responseStreamObserver());
        for (int i = 1; i < 3; i++) {
            AccountInfoRequest accountInfoRequest = AccountInfoRequest.newBuilder()
                    .setId(i)
                    .build();
            requestStreamObserver.onNext(accountInfoRequest);
        }
        requestStreamObserver.onCompleted();
    }

    public static void main(String[] args) throws InterruptedException {
        new BidirectionalStreamingClient().run();
    }
}
