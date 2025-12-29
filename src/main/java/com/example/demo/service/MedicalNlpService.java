package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.exception.UpstreamServiceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class MedicalNlpService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${nlpcloud.api.key}")
    private String apiKey;

    @Value("${nlpcloud.model}")
    private String model;

    public GrammarResponse checkGrammar(ClinicalNoteRequest request) {
        return postForResponse("/grammar", request, this::mapGrammarResponse);
    }

    public EntityExtractionResponse extractEntities(ClinicalNoteRequest request) {
        return postForResponse("/entities", request, this::mapEntityResponse);
    }

    public SummaryResponse summarize(ClinicalNoteRequest request) {
        return postForResponse("/summarize", request, this::mapSummaryResponse);
    }

    public KeywordsResponse keywords(ClinicalNoteRequest request) {
        return postForResponse("/keywords", request, this::mapKeywordsResponse);
    }

    private <T> T postForResponse(String path,
                                  ClinicalNoteRequest request,
                                  ResponseMapper<T> mapperFunction) {
        String url = "https://api.nlpcloud.io/v1/" + model + path;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + apiKey);

        Map<String, String> payload = new HashMap<>();
        payload.put("text", request.getNote());
        if (request.getPatientContext() != null && !request.getPatientContext().isEmpty()) {
            payload.put("context", request.getPatientContext());
        }

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            if (response.getBody() == null) {
                throw new UpstreamServiceException("Upstream NLP service returned an empty response");
            }
            JsonNode jsonNode = mapper.readTree(response.getBody());
            return mapperFunction.map(jsonNode);
        } catch (RestClientResponseException e) {
            throw new UpstreamServiceException("Upstream service responded with status " + e.getRawStatusCode(), e);
        } catch (RestClientException e) {
            throw new UpstreamServiceException("Failed to reach upstream NLP service", e);
        } catch (IOException e) {
            throw new UpstreamServiceException("Unable to parse upstream NLP response", e);
        }
    }

    private GrammarResponse mapGrammarResponse(JsonNode jsonNode) throws IOException {
        String corrected = jsonNode.path("corrected_text").asText(jsonNode.path("text").asText(""));
        List<String> feedback = new ArrayList<>();
        if (jsonNode.has("feedback") && jsonNode.get("feedback").isArray()) {
            jsonNode.get("feedback").forEach(node -> feedback.add(node.asText()));
        }
        if (feedback.isEmpty() && jsonNode.has("corrections")) {
            jsonNode.get("corrections").forEach(node -> feedback.add(node.asText()));
        }
        return new GrammarResponse(corrected, feedback);
    }

    private EntityExtractionResponse mapEntityResponse(JsonNode jsonNode) throws IOException {
        List<MedicalEntity> entities = new ArrayList<>();
        if (jsonNode.has("entities")) {
            jsonNode.get("entities").forEach(node -> {
                String text = node.path("text").asText("");
                String type = node.path("type").asText("");
                int start = node.path("start").asInt(0);
                int end = node.path("end").asInt(0);
                entities.add(new MedicalEntity(text, type, start, end));
            });
        }
        return new EntityExtractionResponse(entities);
    }

    private SummaryResponse mapSummaryResponse(JsonNode jsonNode) {
        String summary = jsonNode.path("summary_text").asText(jsonNode.path("summary").asText(""));
        return new SummaryResponse(summary);
    }

    private KeywordsResponse mapKeywordsResponse(JsonNode jsonNode) {
        List<String> keywords = new ArrayList<>();
        if (jsonNode.has("keywords")) {
            jsonNode.get("keywords").forEach(node -> keywords.add(node.asText()));
        }
        return new KeywordsResponse(keywords);
    }

    @FunctionalInterface
    private interface ResponseMapper<T> {
        T map(JsonNode jsonNode) throws IOException;
    }
}
