package com.example.demo.dto;

import java.util.List;

public class KeywordsResponse {
    private List<String> keywords;

    public KeywordsResponse() {
    }

    public KeywordsResponse(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
