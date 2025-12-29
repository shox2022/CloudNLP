package com.example.demo.dto;

import java.util.List;

public class GrammarResponse {
    private String correctedText;
    private List<String> feedback;

    public GrammarResponse() {
    }

    public GrammarResponse(String correctedText, List<String> feedback) {
        this.correctedText = correctedText;
        this.feedback = feedback;
    }

    public String getCorrectedText() {
        return correctedText;
    }

    public void setCorrectedText(String correctedText) {
        this.correctedText = correctedText;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<String> feedback) {
        this.feedback = feedback;
    }
}
