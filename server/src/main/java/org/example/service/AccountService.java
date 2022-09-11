package org.example.service;

import org.example.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private List<Account> accounts;

    public AccountService() {
        accounts = new ArrayList<>(){
            {
                add(new Account(1, "Nguyen", "A", 1, 8, 1_000L));
                add(new Account(2, "Tran", "B", 2, 7, 2_000L));
                add(new Account(3, "Vo", "C", 1, 4, 1_300L));
            }
        };
    }

    public Account findAccountById(int id) {
        return accounts.stream().filter(acc -> acc.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Account> findAccountsByDepartmentId(int departmentId) {
        return accounts.stream().filter(acc -> acc.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }
}
