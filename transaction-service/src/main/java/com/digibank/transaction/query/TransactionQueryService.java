package com.digibank.transaction.query;

import com.digibank.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionQueryService {

    private final TransactionRepository repository;

    public TransactionQueryService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<TransactionView> getAllViews() {
        return repository.findAll()
                .stream()
                .map(tx -> new TransactionView(tx.getId(), tx.getAccountId(), tx.getAmount(), tx.getType()))
                .toList();
    }
}
