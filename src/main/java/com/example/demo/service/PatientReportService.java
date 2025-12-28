package com.example.demo.service;

import com.example.demo.entity.PatientReport;
import com.example.demo.repository.PatientReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientReportService {

    private final PatientReportRepository repository;

    public PatientReportService(PatientReportRepository repository) {
        this.repository = repository;
    }

    public PatientReport create(PatientReport report) {
        return repository.save(report);
    }

    public List<PatientReport> getAll() {
        return repository.findAll();
    }

    public PatientReport getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public PatientReport update(Long id, PatientReport newReport) {
        PatientReport report = getById(id);
        report.setPatientName(newReport.getPatientName());
        report.setReportText(newReport.getReportText());
        return repository.save(report);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}