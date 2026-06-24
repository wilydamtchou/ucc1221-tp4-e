package com.digibank.transaction.command;

public record CreateTransactionCommand(Long accountId, Double amount, String type) {
}
