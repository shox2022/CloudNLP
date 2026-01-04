package com.example.demo.service;

import com.example.demo.config.NlpCloudClientConfig;
import com.example.demo.config.NlpCloudProperties;
import com.example.demo.dto.ClinicalNoteRequest;
import com.example.demo.dto.GrammarResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

@Service
public class GrammarService extends BaseNlpCloudService {

    private static final Logger log = LoggerFactory.getLogger(GrammarService.class);

    public GrammarService(RestTemplate rt, NlpCloudProperties props) {
        super(rt, props);
    }

    public GrammarResponse checkGrammar(ClinicalNoteRequest request) {
        String path = "/gpu/" + properties.getGrammarModel() + properties.getGrammarEndpoint();

        log.info("Grammar model: {}", properties.getGrammarModel());
        log.info("Grammar endpoint: {}", properties.getGrammarEndpoint());
        log.info("Full grammar path: {}", path);
        return executeWithRetry(() -> {
            var payload = Map.of("text", request.getNote());
            log.debug("Grammar request payload keys → {}", payload.keySet());
            log.debug("Grammar request text length  → {}",
                    request.getNote() != null ? request.getNote().length() : 0);
            String response = restTemplate.postForObject(
                    path, buildRequest(payload), String.class
            );
            log.info("NLP Cloud response status → {}", response);

            return new GrammarResponse(response, List.of());
        });
    }
}