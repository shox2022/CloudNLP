package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "nlpcloud")
public class NlpCloudProperties {

    // ===== Common =====

    @NotBlank
    private String apiKey;

    private String baseUrl = "https://api.nlpcloud.io/v1";

    private Duration timeout = Duration.ofSeconds(5);

    private int maxRetries = 3;

    // ===== Summarization =====

    @NotBlank
    private String summarizationModel;

    private String summarizationEndpoint = "/summarization";

    // ===== Entity Extraction (NER) =====

    @NotBlank
    private String entityModel;

    private String entityEndpoint = "/entities";

    // ===== Getters / Setters =====

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public String getSummarizationModel() {
        return summarizationModel;
    }

    public void setSummarizationModel(String summarizationModel) {
        this.summarizationModel = summarizationModel;
    }

    public String getSummarizationEndpoint() {
        return summarizationEndpoint;
    }

    public void setSummarizationEndpoint(String summarizationEndpoint) {
        this.summarizationEndpoint = summarizationEndpoint;
    }

    public String getEntityModel() {
        return entityModel;
    }

    public void setEntityModel(String entityModel) {
        this.entityModel = entityModel;
    }

    public String getEntityEndpoint() {
        return entityEndpoint;
    }

    public void setEntityEndpoint(String entityEndpoint) {
        this.entityEndpoint = entityEndpoint;
    }
}
