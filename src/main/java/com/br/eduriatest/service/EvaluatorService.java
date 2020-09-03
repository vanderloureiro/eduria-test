package com.br.eduriatest.service;

import com.br.eduriatest.client.EvaluatorClient;
import com.br.eduriatest.model.AnswerQuestionForm;
import com.br.eduriatest.model.EvaluatorTestForm;
import com.br.eduriatest.model.EvaluatorTestResult;
import com.br.eduriatest.model.QuestionPresentedDto;
import com.br.eduriatest.model.StudentLevel;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {

	private int CORRECT_ALTERNATIVE = 3;
	private int WRONG_ALTERNATIVE   = 1;
	
    private EvaluatorClient evaluatorClient;

    EvaluatorService(EvaluatorClient evaluatorClient) {
        this.evaluatorClient = evaluatorClient;
    }

    public EvaluatorTestResult testEvaluator(EvaluatorTestForm form) {
    	
    	this.answerAllQuestions(form);
    	this.getFinalResult();
        return null;
    }
    
    private void getFinalResult() {
    	// todo
    }
    
    private void answerAllQuestions(EvaluatorTestForm form) {
    	for(int i = 0; i < 50; i++) {
    		ResponseEntity<QuestionPresentedDto> presentatedQuestion = this.evaluatorClient.getQuestion(form.getIdEnrollment());
    		AnswerQuestionForm answerForm = new AnswerQuestionForm();
    		answerForm.setEnrollmentId(form.getIdEnrollment());
    		answerForm.setQuestionId(presentatedQuestion.getBody().getQuestionId());
    		answerForm.setSelectedAlternative(this.answerByStudentLevel(form.getStudentLevel(), i));
    	}
    }
    
    private int answerByStudentLevel(StudentLevel level, int indexFor) {
    	if (level == StudentLevel.ADVANCED) 
    		return this.CORRECT_ALTERNATIVE;	
    	
    	if (level == StudentLevel.BEGINNER) 
    		return (indexFor % 5 == 0) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;
    	
    	return (indexFor % 2 == 0) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;
    }
    
    
    
    
}