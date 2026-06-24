package com.digibank.compliance.api;

import com.digibank.compliance.service.ComplianceService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

    private final ComplianceService service;

    public ComplianceController(ComplianceService service) {
        this.service = service;
    }

    @GetMapping("/validate/{amount}")
    public Map<String, Boolean> validate(@PathVariable Double amount) {
        return Map.of("valid", service.validateTransactionAmount(amount));
    }
}
