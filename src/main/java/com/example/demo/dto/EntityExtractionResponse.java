package com.example.demo.dto;

import java.util.List;

public class EntityExtractionResponse {
    private List<MedicalEntity> entities;

    public EntityExtractionResponse() {
    }

    public EntityExtractionResponse(List<MedicalEntity> entities) {
        this.entities = entities;
    }

    public List<MedicalEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<MedicalEntity> entities) {
        this.entities = entities;
    }
}
