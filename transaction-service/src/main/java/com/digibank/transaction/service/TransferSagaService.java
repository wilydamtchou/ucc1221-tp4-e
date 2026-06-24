package com.digibank.transaction.service;

import com.digibank.transaction.client.AccountClient;
import com.digibank.transaction.client.ComplianceClient;
import com.digibank.transaction.client.NotificationClient;
import org.springframework.stereotype.Service;

@Service
public class TransferSagaService {

    private final ComplianceClient complianceClient;
    private final AccountClient accountClient;
    private final NotificationClient notificationClient;

    public TransferSagaService(
            ComplianceClient complianceClient,
            AccountClient accountClient,
            NotificationClient notificationClient) {
        this.complianceClient = complianceClient;
        this.accountClient = accountClient;
        this.notificationClient = notificationClient;
    }

    public boolean executeTransfer(Long fromAccountId, Long toAccountId, Double amount) {
        if (!complianceClient.validateAmount(amount)) {
            return false;
        }

        boolean debited = accountClient.debit(fromAccountId, amount);
        if (!debited) {
            return false;
        }

        boolean credited = accountClient.credit(toAccountId, amount);
        if (!credited) {
            accountClient.compensateDebit(fromAccountId, amount);
            return false;
        }

        boolean notified = notificationClient.sendTransferConfirmation(fromAccountId, toAccountId, amount);
        if (!notified) {
            accountClient.compensateDebit(fromAccountId, amount);
            accountClient.compensateCredit(toAccountId, amount);
            return false;
        }

        return true;
    }
}
