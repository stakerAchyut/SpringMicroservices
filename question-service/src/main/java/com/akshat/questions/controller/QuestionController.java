package com.akshat.questions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshat.questions.pojo.QuestionWrapper;
import com.akshat.questions.pojo.Response;
import com.akshat.questions.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {
	
	private QuestionService questionService;

	public QuestionService getQuestionService() {
		return questionService;
	}

	@Autowired
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping("type/{type}")
	public List<QuestionWrapper> getQuestionsByType(@PathVariable String type) {
		return questionService.getQuestionsByType(type);
	}
	
	@PostMapping("submit")
	public Integer getScore(@RequestBody List<Response> responses) {
		return questionService.calculateScore(responses);
	}
}
