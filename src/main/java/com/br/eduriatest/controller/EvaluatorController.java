package com.br.eduriatest.controller;

import com.br.eduriatest.client.EvaluatorClient;
import com.br.eduriatest.model.EnrollmentDto;
import com.br.eduriatest.model.EvaluatorTestForm;
import com.br.eduriatest.model.EvaluatorTestResult;
import com.br.eduriatest.service.EvaluatorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private EvaluatorClient client;

    public EvaluatorController(EvaluatorService evaluatorService, EvaluatorClient client) {
        this.evaluatorService = evaluatorService;
        this.client = client;
    }

    @PostMapping
    @ApiOperation(value = "Test a enrollment", response = EvaluatorTestResult.class)
    public ResponseEntity<EvaluatorTestResult> testEvaluator(@RequestBody EvaluatorTestForm form) {
        return ResponseEntity.ok().body(this.evaluatorService.testEvaluator(form));
    }
    

    @GetMapping("{enrollmentId}")
    @ApiOperation(value = "Test a enrollment", response = EnrollmentDto.class)
    public ResponseEntity<EnrollmentDto> getEnrollment(@PathVariable Long enrollmentId) {
        return this.client.getEnrollment(enrollmentId);
    }
}