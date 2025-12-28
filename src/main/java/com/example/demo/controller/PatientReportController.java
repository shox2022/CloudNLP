package com.example.demo.controller;

import com.example.demo.entity.PatientReport;
import com.example.demo.service.PatientReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class PatientReportController {

    private final PatientReportService service;

    public PatientReportController(PatientReportService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public PatientReport create(@RequestBody PatientReport report) {
        return service.create(report);
    }

    // READ ALL
    @GetMapping
    public List<PatientReport> getAll() {
        return service.getAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public PatientReport getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PatientReport update(@PathVariable Long id,
                                @RequestBody PatientReport report) {
        return service.update(id, report);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}