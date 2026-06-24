package com.digibank.notification.service;

import com.digibank.notification.model.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendNotification(NotificationRequest request) {
        return "Notification sent to " + request.getRecipient() + ": " + request.getMessage();
    }
}
