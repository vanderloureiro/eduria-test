package com.br.eduriatest.client;

import com.br.eduriatest.model.AnswerQuestionForm;
import com.br.eduriatest.model.QuestionPresentedDto;
import com.br.eduriatest.model.ResponseResultDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EvaluatorClient {

    private final String RESOURCE_URL = "http://localhost:3332/evaluator/";

    private final RestTemplate restTemplate;

    public EvaluatorClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<QuestionPresentedDto> getQuestion(Long enrollmentId) {
        return this.restTemplate.getForEntity(
            this.RESOURCE_URL + enrollmentId, QuestionPresentedDto.class);
    }

    public ResponseEntity<ResponseResultDto> answerQuestion(AnswerQuestionForm form) {
        return this.restTemplate.postForEntity(this.RESOURCE_URL, form, ResponseResultDto.class);
    }
    
}