package com.example.demo.service;


import com.example.demo.dto.ClassificationRequest;
import com.example.demo.dto.ClassificationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NlpCloudService {

    @Value("${nlpcloud.api.key}")
    private String apiKey;

    @Value("${nlpcloud.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

    public ClassificationResponse classify(String text) {

        String url = "https://api.nlpcloud.io/v1/" + model + "/classification";

        ClassificationRequest requestBody = new ClassificationRequest(
                text,
                List.of("space", "sport", "business", "journalism", "politics"),
                true
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + apiKey);

        HttpEntity<ClassificationRequest> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<ClassificationResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        ClassificationResponse.class
                );

        return response.getBody();
    }
}