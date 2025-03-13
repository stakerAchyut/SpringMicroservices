package com.akshat.questions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshat.questions.model.Question;
import com.akshat.questions.pojo.QuestionWrapper;
import com.akshat.questions.pojo.Response;
import com.akshat.questions.repository.QuestionRepository;

@Service
public class QuestionService {
	
	private QuestionRepository questionRepository;

	public QuestionRepository getQuestionRepository() {
		return questionRepository;
	}

	@Autowired
	public void setQuestionRepository(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	public List<QuestionWrapper> getQuestionsByType(String type) {
		
		QuestionWrapper wrapper = null;
		List<QuestionWrapper> list = new ArrayList<>();

		List<Question> questionList = questionRepository.getQuestionByType(type);		
		
		for (Question ques : questionList) {
			wrapper = new QuestionWrapper();
			
			wrapper.setId(ques.getId());
			wrapper.setQuestion(ques.getQuestion());
			wrapper.setOption1(ques.getOption1());
			wrapper.setOption2(ques.getOption2());
			wrapper.setOption3(ques.getOption3());
			wrapper.setOption4(ques.getOption4());

			list.add(wrapper);
		}
		
		return list;
	}

	public Integer calculateScore(List<Response> responses) {
		
		int score = 0;
		
		List<Question> list = questionRepository.getQuestionsByIds(responses.stream().map(res -> res.getId()).toList());
		
		int i = 0;
		for (Response res : responses) {
			if (res.getAnswer().equals(list.get(i).getAnswer())) score++;
			i++;
		}
		return score;
	}
}
