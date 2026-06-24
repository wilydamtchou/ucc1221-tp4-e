package com.digibank.notification.api;

import com.digibank.notification.model.NotificationRequest;
import com.digibank.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public String notify(@RequestBody NotificationRequest request) {
        return service.sendNotification(request);
    }
}
