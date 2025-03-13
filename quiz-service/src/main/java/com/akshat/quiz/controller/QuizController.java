package com.akshat.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshat.quiz.pojo.QuestionWrapper;
import com.akshat.quiz.pojo.Response;
import com.akshat.quiz.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

	private QuizService quizService;
	
	public QuizService getQuizService() {
		return quizService;
	}

	@Autowired
	public void setQuizService(QuizService quizService) {
		this.quizService = quizService;
	}

	@PostMapping("create")
	public String createQuiz(@RequestParam String type, @RequestParam String title) {
		return quizService.createQuiz(type, title);
	}
	
	@GetMapping("get/{type}")
	public List<QuestionWrapper> getQuizQuestions(@PathVariable String type) {
		return quizService.getQuiz(type);
	}
	
	@PostMapping("submit")
	public Integer calculateScore(@RequestBody List<Response> responses) {
		return quizService.calculateResult(responses);
	}
	
}
