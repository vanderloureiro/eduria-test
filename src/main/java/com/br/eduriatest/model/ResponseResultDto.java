package com.br.eduriatest.model;

import lombok.Data;

@Data
public class ResponseResultDto {
    
    private Long questionId;
    private Long enrollmentId;
    private Double score;
    private boolean isCorrectResponse;
    private String correctAlternative;
}