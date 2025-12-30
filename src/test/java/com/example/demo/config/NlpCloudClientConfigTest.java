package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class NlpCloudClientConfigTest {

    @Test
    void appendsVersionPrefixWhenMissing() {
        NlpCloudProperties properties = new NlpCloudProperties();
        properties.setBaseUrl("https://mock.nlpcloud.io");
        properties.setTimeout(Duration.ofSeconds(2));
        properties.getModels().setGrammar("grammar-test-model");
        properties.getModels().setEntities("entities-test-model");
        properties.getModels().setSummarize("summarize-test-model");
        properties.getModels().setKeywords("keywords-test-model");
        properties.getModels().setClassification("classification-test-model");

        RestTemplate restTemplate = new NlpCloudClientConfig()
                .nlpCloudRestTemplate(properties, new RestTemplateBuilder());

        URI expanded = restTemplate.getUriTemplateHandler().expand("/grammar");

        assertThat(expanded.toString()).isEqualTo("https://mock.nlpcloud.io/v1/grammar");
    }
}
