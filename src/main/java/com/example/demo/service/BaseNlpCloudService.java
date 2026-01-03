package com.example.demo.service;

import com.example.demo.config.NlpCloudProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

public abstract class BaseNlpCloudService {

    protected final RestTemplate restTemplate;
    protected final NlpCloudProperties properties;

    protected BaseNlpCloudService(RestTemplate restTemplate, NlpCloudProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    protected HttpHeaders authHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Token " + properties.getApiKey());
        return headers;
    }

    protected <T> T executeWithRetry(String path, Supplier<T> action) {
        int attempts = Math.min(Math.max(properties.getMaxRetries(), 1), 3);

        for (int i = 1; i <= attempts; i++) {
            try {
                return action.get();
            } catch (Exception ex) {
                if (i == attempts) throw ex;
                try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            }
        }
        throw new RuntimeException("Unreachable");
    }
}
