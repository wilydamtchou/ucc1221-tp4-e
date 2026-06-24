package com.digibank.transaction.client;

import org.springframework.stereotype.Component;

@Component
public class AccountClient {

    public boolean debit(Long fromAccountId, Double amount) {
        // @TODO implement method
        return true;
    }

    public boolean credit(Long toAccountId, Double amount) {
        // @TODO implement method
        return true;
    }

    public void compensateDebit(Long fromAccountId, Double amount) {
        // @TODO implement method
    }

    public void compensateCredit(Long toAccountId, Double amount) {
        // @TODO implement method
    }
}
