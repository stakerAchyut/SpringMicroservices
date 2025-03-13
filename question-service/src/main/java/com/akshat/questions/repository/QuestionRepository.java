package com.akshat.questions.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.akshat.questions.model.Question;

@Repository
public class QuestionRepository {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Question> getQuestionByType(String type) {
		List<Question> list = null;
		String queryStr = "FROM Question WHERE type = :type";
		try {
			Query<Question> query = sessionFactory.getCurrentSession().createQuery(queryStr, Question.class);
			query.setParameter("type", type);
			
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Question> getQuestionsByIds(List<Integer> values) {
		List<Question> list = null;
		String queryStr = "FROM Question WHERE id IN (:id)";
		try {
			Query<Question> query = sessionFactory.getCurrentSession().createQuery(queryStr, Question.class);
			query.setParameterList("id", values);
			
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
