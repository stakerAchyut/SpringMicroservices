package com.akshat.quiz.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akshat.quiz.model.Quiz;

@Repository
public class QuizRepository {

	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}
	
	@Autowired
	public void setSession(SessionFactory session) {
		this.session = session;
	}
	
	public void save(Quiz quiz) {
		session.getCurrentSession().persist(quiz);
	}

	public Quiz getQuizByType(String type) {
		Quiz quiz = null;
		List<Quiz> list = null;
		String queryStr = "FROM Quiz WHERE type = :type";
		try {
			Query<Quiz> query = session.getCurrentSession().createNamedQuery(queryStr, Quiz.class);
			query.setParameter("type", type);
			
			list = query.list();
			if (list != null && !list.isEmpty()) {
				quiz = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quiz;
	}
}
