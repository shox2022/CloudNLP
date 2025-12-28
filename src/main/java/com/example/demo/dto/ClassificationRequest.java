package com.example.demo.dto;

import java.util.List;

public class ClassificationRequest {
    private String text;
    private List<String> labels;
    private boolean multi_class;

    public ClassificationRequest(String text, List<String> labels, boolean multi_class) {
        this.text = text;
        this.labels = labels;
        this.multi_class = multi_class;
    }

    public String getText() { return text; }
    public List<String> getLabels() { return labels; }
    public boolean isMulti_class() { return multi_class; }
}