package org.example.grpc.serversidestream;

import com.example.grpc.AccountInfoResponse;
import com.example.grpc.AccountsInDepartmentRequest;
import io.grpc.stub.StreamObserver;
import org.example.entity.Account;
import org.example.service.AccountService;

import java.util.List;

public class DepartmentServiceGrpc extends com.example.grpc.DepartmentServiceGrpc.DepartmentServiceImplBase {

    @Override
    public void getListAccountInDepartment(AccountsInDepartmentRequest request, StreamObserver<AccountInfoResponse> responseObserver) {
        System.out.println("[Server][ServerSideStreaming] Received request");
        List<Account> accounts = new AccountService().findAccountsByDepartmentId(request.getId());
        for (Account account : accounts) {
            AccountInfoResponse response = AccountInfoResponse.newBuilder()
                    .setId(account.getId())
                    .setFirstName(account.getFirstName())
                    .setLastName(account.getLastName())
                    .build();

            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }
}
