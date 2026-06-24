package com.digibank.transaction.service;

import com.digibank.transaction.client.ComplianceClient;
import com.digibank.transaction.model.Transaction;
import com.digibank.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final ComplianceClient complianceClient;

    public TransactionService(TransactionRepository repository, ComplianceClient complianceClient) {
        this.repository = repository;
        this.complianceClient = complianceClient;
    }

    public Transaction createTransaction(Transaction transaction) {
        if (!complianceClient.validateAmount(transaction.getAmount())) {
            throw new IllegalArgumentException("Transaction rejected by compliance");
        }
        return repository.save(transaction);
    }

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }
}
