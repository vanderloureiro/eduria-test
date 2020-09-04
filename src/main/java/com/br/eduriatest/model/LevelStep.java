package com.br.eduriatest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LevelStep {
    
    private LevelQuestionEnum presentedQuestionLevel;
    private boolean correctAnswer;
    private Double score;
}