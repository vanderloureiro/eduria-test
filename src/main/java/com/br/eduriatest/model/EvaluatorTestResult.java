package com.br.eduriatest.model;

import java.util.List;

import lombok.Data;

@Data
public class EvaluatorTestResult {
    
    private Double score;
    private StateEnum state;

    private List<LevelStep> levelStepList;
}