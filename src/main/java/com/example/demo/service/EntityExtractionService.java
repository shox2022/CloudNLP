package com.example.demo.service;

import com.example.demo.config.NlpCloudProperties;
import com.example.demo.dto.ClinicalNoteRequest;
import com.example.demo.dto.EntityExtractionResponse;
import com.example.demo.mapper.NlpCloudMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EntityExtractionService extends BaseNlpCloudService {

    private final NlpCloudMapper mapper;

    public EntityExtractionService(
            RestTemplate restTemplate,
            NlpCloudProperties properties,
            NlpCloudMapper mapper
    ) {
        super(restTemplate, properties);
        this.mapper = mapper;
    }

    public EntityExtractionResponse extractEntities(ClinicalNoteRequest request) {
        Map<String, String> payload = Map.of("text", request.getNote());

        String path = "/" + properties.getEntityModel()
                + properties.getEntityEndpoint();

        return executeWithRetry(path, () -> {
            HttpEntity<Map<String, String>> entity =
                    new HttpEntity<>(payload, authHeaders());

            ResponseEntity<String> response =
                    restTemplate.postForEntity(path, entity, String.class);

            return mapper.toEntityExtractionResponse(response.getBody());
        });
    }
}