package com.akshat.quiz.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.akshat.quiz.pojo.QuestionWrapper;
import com.akshat.quiz.pojo.Response;

@FeignClient(name = "question-service")
public interface FeignInterface {

	@GetMapping("type/{type}")
	public List<QuestionWrapper> getQuestionsByType(@PathVariable String type);

	@PostMapping("submit")
	public Integer getScore(@RequestBody List<Response> responses);
}
