package org.example.grpc.simple;

import com.example.grpc.AccountInfoRequest;
import com.example.grpc.AccountInfoResponse;
import io.grpc.stub.StreamObserver;
import org.example.entity.Account;
import org.example.service.AccountService;

public class AccountServiceGrpc extends com.example.grpc.AccountServiceGrpc.AccountServiceImplBase {

    @Override
    public void getAccountInfo(AccountInfoRequest request, StreamObserver<AccountInfoResponse> responseObserver) {
        System.out.println("[Server][Simple] Received request");
        Account account = new AccountService().findAccountById(request.getId());

        AccountInfoResponse response = AccountInfoResponse.newBuilder()
                .setId(account.getId())
                .setFirstName(account.getFirstName())
                .setLastName(account.getLastName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
