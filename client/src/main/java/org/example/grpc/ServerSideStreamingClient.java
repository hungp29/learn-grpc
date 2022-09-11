package org.example.grpc;

import com.example.grpc.AccountInfoResponse;
import com.example.grpc.AccountsInDepartmentRequest;
import com.example.grpc.DepartmentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class ServerSideStreamingClient extends Client {

    private final DepartmentServiceGrpc.DepartmentServiceBlockingStub blockingStub;

    public ServerSideStreamingClient() {
        blockingStub  = DepartmentServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public void handle() {
        AccountsInDepartmentRequest accountsInDepartmentRequest = AccountsInDepartmentRequest.newBuilder()
                .setId(1)
                .build();

        Iterator<AccountInfoResponse> accountIterator = blockingStub.getListAccountInDepartment(accountsInDepartmentRequest);
        while (accountIterator.hasNext()) {
            AccountInfoResponse response = accountIterator.next();
            System.out.printf("%d - %s %s", response.getId(), response.getFirstName(), response.getLastName());
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ServerSideStreamingClient().run();
    }
}
