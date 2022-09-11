package org.example.grpc.clientsidestream;

import com.example.grpc.AccountInfoRequest;
import com.example.grpc.NumOfProductResponse;
import io.grpc.stub.StreamObserver;
import org.example.entity.Account;
import org.example.service.AccountService;

public class ProductServiceGrpc extends com.example.grpc.ProductServiceGrpc.ProductServiceImplBase {

    @Override
    public StreamObserver<AccountInfoRequest> countProduct(StreamObserver<NumOfProductResponse> responseObserver) {
        AccountService accountService = new AccountService();
        return new StreamObserver<>() {
            int numOfProduct = 0;

            @Override
            public void onNext(AccountInfoRequest accountInfoRequest) {
                System.out.println("[Server][ClientSideStreaming] Received data from client stream");
                Account account = accountService.findAccountById(accountInfoRequest.getId());
                if (null != account) {
                    numOfProduct += account.getNumOfProduct();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Server][ClientSideStreaming] Error " + throwable.getMessage());
                //TODO: handling error
            }

            @Override
            public void onCompleted() {
                System.out.println("[Server][ClientSideStreaming] Received completed signal");
                NumOfProductResponse response = NumOfProductResponse.newBuilder()
                        .setNumOfProduct(numOfProduct)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
