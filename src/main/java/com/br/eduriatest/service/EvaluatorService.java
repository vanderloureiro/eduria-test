package com.br.eduriatest.service;

import java.util.ArrayList;
import java.util.List;

import com.br.eduriatest.client.EvaluatorClient;
import com.br.eduriatest.model.AnswerQuestionForm;
import com.br.eduriatest.model.EnrollmentDto;
import com.br.eduriatest.model.EvaluatorTestForm;
import com.br.eduriatest.model.EvaluatorTestResult;
import com.br.eduriatest.model.LevelStep;
import com.br.eduriatest.model.QuestionPresentedDto;
import com.br.eduriatest.model.ResponseResultDto;
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
    	
		List<LevelStep> levelStepList = this.answerAllQuestions(form);
		ResponseEntity<EnrollmentDto> enrollment = this.evaluatorClient.getEnrollment(form.getIdEnrollment());
		
		System.out.println(enrollment.getBody().toString());
		EvaluatorTestResult result = new EvaluatorTestResult();
		result.setScore(enrollment.getBody().getScore());
		result.setState(enrollment.getBody().getState());
		result.setLevelStepList(levelStepList);

        return result;
    }
    
    private List<LevelStep> answerAllQuestions(EvaluatorTestForm form) {
		List<LevelStep> levelStepList = new ArrayList<>();
    	for(int i = 0; i < 50; i++) {
			levelStepList.add(this.answerOneQuestion(form, i));
		}
		return levelStepList;
	}
	
	private LevelStep answerOneQuestion(EvaluatorTestForm form, int indexStep) {
		QuestionPresentedDto presentatedQuestion = this.evaluatorClient.getQuestion(form.getIdEnrollment()).getBody();
		
		AnswerQuestionForm answerForm = new AnswerQuestionForm();
		answerForm.setEnrollmentId(form.getIdEnrollment());
		answerForm.setQuestionId(presentatedQuestion.getQuestionId());
		answerForm.setSelectedAlternative(this.answerByStudentLevel(form.getStudentLevel(), indexStep));
		
		ResponseResultDto resultAnswer = this.evaluatorClient.answerQuestion(answerForm).getBody();
		
		return new LevelStep(presentatedQuestion.getQuestionLevel(), resultAnswer.isCorrectResponse(), resultAnswer.getScore());
	}
    
    private int answerByStudentLevel(StudentLevel level, int indexFor) {
    	if (level == StudentLevel.ADVANCED) 
    		return this.CORRECT_ALTERNATIVE;	
    	
    	if (level == StudentLevel.BEGINNER) 
    		return (indexFor % 3 != 0) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;
    	
    	return (indexFor % 5 != 0) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;
    }
    
}