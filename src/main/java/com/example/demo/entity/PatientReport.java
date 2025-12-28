package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class PatientReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    @Column(length = 5000)
    private String reportText;

    public PatientReport() {}

    public PatientReport(String patientName, String reportText) {
        this.patientName = patientName;
        this.reportText = reportText;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getReportText() { return reportText; }
    public void setReportText(String reportText) { this.reportText = reportText; }
}
