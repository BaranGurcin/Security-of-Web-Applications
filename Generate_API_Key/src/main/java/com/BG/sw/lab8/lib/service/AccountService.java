package com.BG.sw.lab8.lib.service;

import java.util.ArrayList;
import java.util.List;

import com.BG.sw.lab8.lib.dto.Account;


public class AccountService {

    private final List<Account> accounts = new ArrayList<>(
            List.of(
                new Account("1", "Alice"),
                new Account("2", "Bob"),
                new Account("3", "Charlie")
            )
    );

    public List<Account> getAllAccounts() {
        return List.copyOf(accounts);
    }
}
