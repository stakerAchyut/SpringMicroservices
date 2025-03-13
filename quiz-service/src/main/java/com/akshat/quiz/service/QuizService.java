package com.akshat.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshat.quiz.config.FeignInterface;
import com.akshat.quiz.model.Quiz;
import com.akshat.quiz.pojo.QuestionWrapper;
import com.akshat.quiz.pojo.Response;
import com.akshat.quiz.repository.QuizRepository;

@Service
public class QuizService {
	
	private FeignInterface feignInterface;
	private QuizRepository quizRepository;

	public FeignInterface getFeignInterface() {
		return feignInterface;
	}

	@Autowired
	public void setFeignInterface(FeignInterface feignInterface) {
		this.feignInterface = feignInterface;
	}

	public QuizRepository getQuizRepository() {
		return quizRepository;
	}

	@Autowired
	public void setQuizRepository(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}

	public String createQuiz(String type, String title) {
		
		List<QuestionWrapper> list = feignInterface.getQuestionsByType(type);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setType(type);
		quiz.setQuestions(list.stream().map(QuestionWrapper::getId).toList());
		
		quizRepository.save(quiz);
		 
		return "SUCCESS";
	}

	public List<QuestionWrapper> getQuiz(String type) {
		
		List<QuestionWrapper> list = feignInterface.getQuestionsByType(type); 
		
		return list;
	}

	public Integer calculateResult(List<Response> responses) { 
		
		Integer score = feignInterface.getScore(responses);
		
		return score;
	}
	
	
}
