package com.example.demo.controller;

import com.example.demo.dto.ClassificationResponse;
import com.example.demo.service.NlpCloudService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AnalysisController {

    private final NlpCloudService service;

    public AnalysisController(NlpCloudService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/analyze")
    @ResponseBody
    public ClassificationResponse analyze(@RequestParam String text) {
        return service.classify(text);
    }
}

