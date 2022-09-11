package org.example.grpc.bidirectionalstream;

import com.example.grpc.AccountInfoRequest;
import com.example.grpc.SalaryResponse;
import io.grpc.stub.StreamObserver;
import org.example.entity.Account;
import org.example.service.AccountService;

public class SalaryServiceGrpc extends com.example.grpc.SalaryServiceGrpc.SalaryServiceImplBase {

    @Override
    public StreamObserver<AccountInfoRequest> getSalary(StreamObserver<SalaryResponse> responseObserver) {
        AccountService service = new AccountService();

        return new StreamObserver<>() {
            @Override
            public void onNext(AccountInfoRequest accountInfoRequest) {
                Account account = service.findAccountById(accountInfoRequest.getId());

                if (null != account) {
                    SalaryResponse response = SalaryResponse.newBuilder()
                            .setAccountId(account.getId())
                            .setAccountName(account.getFirstName() + " " + account.getLastName())
                            .setSalary(account.getSalary())
                            .build();
                    responseObserver.onNext(response);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                // TODO: handling error
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
