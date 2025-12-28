package com.example.demo.dto;

import java.util.List;

public class ClassificationResponse {
    private List<String> labels;
    private List<Double> scores;

    public List<String> getLabels() { return labels; }
    public List<Double> getScores() { return scores; }
}