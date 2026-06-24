package com.digibank.transaction.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Component
public class ComplianceClient {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Value("${api.compliance.validate}")
    private String complianceValidateUrl;

    public ComplianceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "complianceService", fallbackMethod = "fallbackValidation")
    public boolean validateAmount(Double amount) {
        String url = gatewayUrl + complianceValidateUrl + amount;

        Boolean result = restTemplate.getForObject(
                url,
                Boolean.class
        );

        return Boolean.TRUE.equals(result);
    }

    public boolean fallbackValidation(Double amount, Throwable ex) {
        return false;
    }
}
