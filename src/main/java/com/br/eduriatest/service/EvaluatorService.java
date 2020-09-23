package com.br.eduriatest.service;

import com.br.eduriatest.client.EvaluatorClient;
import com.br.eduriatest.model.AnswerQuestionForm;
import com.br.eduriatest.model.EvaluatorTestForm;
import com.br.eduriatest.model.QuestionPresentedDto;
import com.br.eduriatest.model.StudentBehavior;

import org.springframework.stereotype.Service;

@Service
public class EvaluatorService {

	private int CORRECT_ALTERNATIVE = 3;
	private int WRONG_ALTERNATIVE   = 1;
	
    private EvaluatorClient evaluatorClient;

    EvaluatorService(EvaluatorClient evaluatorClient) {
        this.evaluatorClient = evaluatorClient;
    }

    public void testEvaluator(EvaluatorTestForm form) {
    	
		for(int index = 0; index < 30; index++) {
			QuestionPresentedDto presentatedQuestion = this.evaluatorClient.getQuestion(form.getIdEnrollment()).getBody();
		
			AnswerQuestionForm answerForm = new AnswerQuestionForm();
			answerForm.setEnrollmentId(form.getIdEnrollment());
			answerForm.setQuestionId(presentatedQuestion.getQuestionId());
			answerForm.setSelectedAlternative(this.answerByStudentLevel(form.getStudentBehavior(), index));
			
			this.evaluatorClient.answerQuestion(answerForm).getBody();
		}
    }
    	    
    private int answerByStudentLevel(StudentBehavior level, int indexFor) {
		if (level == StudentBehavior.ADVANCED) {
			return this.CORRECT_ALTERNATIVE;	
		}
		
		if (level == StudentBehavior.BEGINNER) {
			if (indexFor < 5) { return this.CORRECT_ALTERNATIVE; }

			return (indexFor % 2 == 0) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;
		}

		if (level == StudentBehavior.MEDIUM) {
			if (indexFor < 18) { return this.CORRECT_ALTERNATIVE; }

			return (indexFor % 2 == 0) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;
		}
		
		return (indexFor < 15) ? this.CORRECT_ALTERNATIVE : this.WRONG_ALTERNATIVE;

    }
    
}