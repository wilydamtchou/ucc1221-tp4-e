package com.digibank.transaction.client;

import org.springframework.stereotype.Component;

@Component
public class NotificationClient {
    public boolean sendTransferConfirmation(Long fromAccountId, Long toAccountId, Double amount) {
        // @TODO implement method
        return true;
    }
}
