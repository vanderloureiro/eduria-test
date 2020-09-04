package com.br.eduriatest.client;

import com.br.eduriatest.model.AnswerQuestionForm;
import com.br.eduriatest.model.EnrollmentDto;
import com.br.eduriatest.model.QuestionPresentedDto;
import com.br.eduriatest.model.ResponseResultDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EvaluatorClient {

    private String RESOURCE_URL = "http://localhost:3332/";
    private RestTemplate restTemplate;

    public EvaluatorClient() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<QuestionPresentedDto> getQuestion(Long enrollmentId) {
        return this.restTemplate.getForEntity(
            this.RESOURCE_URL + "evaluator/" + enrollmentId, 
            QuestionPresentedDto.class);
    }

    public ResponseEntity<EnrollmentDto> getEnrollment(Long enrollmentId) {
        return this.restTemplate.getForEntity(
            this.RESOURCE_URL + "enrollment/" + enrollmentId, 
            EnrollmentDto.class);
    }

    public ResponseEntity<ResponseResultDto> answerQuestion(AnswerQuestionForm form) {
        return this.restTemplate.postForEntity(this.RESOURCE_URL + "evaluator/", form, ResponseResultDto.class);
    }
    
}