package com.digibank.transaction.query;

public record TransactionView(Long id, Long accountId, Double amount, String type) {
}
