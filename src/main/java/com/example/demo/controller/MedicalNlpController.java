package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.MedicalNlpService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nlp")
@CrossOrigin
public class MedicalNlpController {

    private final MedicalNlpService medicalNlpService;

    public MedicalNlpController(MedicalNlpService medicalNlpService) {
        this.medicalNlpService = medicalNlpService;
    }

    @PostMapping("/grammar")
    public GrammarResponse grammar(@Valid @RequestBody ClinicalNoteRequest request) {
        return medicalNlpService.checkGrammar(request);
    }

    @PostMapping("/entities")
    public EntityExtractionResponse entities(@Valid @RequestBody ClinicalNoteRequest request) {
        return medicalNlpService.extractEntities(request);
    }

    @PostMapping("/summarize")
    public SummaryResponse summarize(@Valid @RequestBody ClinicalNoteRequest request) {
        return medicalNlpService.summarize(request);
    }

    @PostMapping("/keywords")
    public KeywordsResponse keywords(@Valid @RequestBody ClinicalNoteRequest request) {
        return medicalNlpService.keywords(request);
    }
}
