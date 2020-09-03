package com.br.eduriatest.controller;

import com.br.eduriatest.model.EvaluatorTestForm;
import com.br.eduriatest.model.EvaluatorTestResult;
import com.br.eduriatest.service.EvaluatorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
public class EvaluatorController {

    private EvaluatorService evaluatorService;

    public EvaluatorController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @PostMapping
    public ResponseEntity<EvaluatorTestResult> testEvaluator(@RequestBody EvaluatorTestForm form) {
        return ResponseEntity.ok().body(this.evaluatorService.testEvaluator(form));
    }
    
}