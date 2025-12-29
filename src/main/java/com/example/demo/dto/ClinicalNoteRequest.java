package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClinicalNoteRequest {

    @NotBlank(message = "Clinical note is required")
    @Size(max = 10000, message = "Clinical note must be 10,000 characters or fewer")
    private String note;

    @Size(max = 2000, message = "Context must be 2,000 characters or fewer")
    private String patientContext;

    public ClinicalNoteRequest() {
    }

    public ClinicalNoteRequest(String note, String patientContext) {
        this.note = note;
        this.patientContext = patientContext;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatientContext() {
        return patientContext;
    }

    public void setPatientContext(String patientContext) {
        this.patientContext = patientContext;
    }
}
