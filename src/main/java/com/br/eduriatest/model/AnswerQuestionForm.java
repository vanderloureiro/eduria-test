package com.br.eduriatest.model;

import lombok.Data;

@Data
public class AnswerQuestionForm {
    
    private Long questionId;
    private Long enrollmentId;
    private int selectedAlternative;

}