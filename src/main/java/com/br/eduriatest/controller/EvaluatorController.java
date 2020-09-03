package com.br.eduriatest.controller;

import com.br.eduriatest.model.EvaluatorTestResult;
import com.br.eduriatest.service.EvaluatorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api
@RestController
public class EvaluatorController {

    private EvaluatorService evaluatorService;

    public EvaluatorController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @GetMapping(("/{id}"))
    public ResponseEntity<EvaluatorTestResult> testEvaluator(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.evaluatorService.testEvaluator(id));
    }
    
}