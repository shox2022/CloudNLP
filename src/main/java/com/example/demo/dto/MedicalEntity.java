package com.example.demo.dto;

public class MedicalEntity {
    private String text;
    private String type;
    private int start;
    private int end;

    public MedicalEntity() {
    }

    public MedicalEntity(String text, String type, int start, int end) {
        this.text = text;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
