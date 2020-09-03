package com.br.eduriatest.service;

import com.br.eduriatest.client.EvaluatorClient;
import com.br.eduriatest.model.EvaluatorTestResult;

import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {

    private EvaluatorClient evaluatorClient;

    EvaluatorService(EvaluatorClient evaluatorClient) {
        this.evaluatorClient = evaluatorClient;
    }

    public EvaluatorTestResult testEvaluator(Long id) {
        return null;
    }
    
}