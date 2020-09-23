package com.br.eduriatest.controller;

import com.br.eduriatest.model.EvaluatorTestForm;
import com.br.eduriatest.service.EvaluatorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "Evaluator Test API")
@RequestMapping("/evaluator-test")
@RestController
public class EvaluatorController {

    private EvaluatorService evaluatorService;

    public EvaluatorController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @PostMapping
    @ApiOperation(value = "Test a enrollment")
    public ResponseEntity<?> testEvaluator(@RequestBody EvaluatorTestForm form) {

        this.evaluatorService.testEvaluator(form);
        return ResponseEntity.ok().build();
    }
    
}