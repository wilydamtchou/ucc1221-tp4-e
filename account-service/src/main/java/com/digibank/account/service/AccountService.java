package com.digibank.account.service;

import com.digibank.account.model.Account;
import com.digibank.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(Account account) {
        if (account.getAccountNumber() == null || account.getAccountNumber().isBlank()) {
            account.setAccountNumber("ACC-" + UUID.randomUUID());
        }
        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }
        return repository.save(account);
    }

    public List<Account> getAccounts() {
        return repository.findAll();
    }

    public Account getAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
