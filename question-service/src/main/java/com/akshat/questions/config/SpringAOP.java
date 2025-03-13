package com.akshat.questions.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class SpringAOP {

	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Around("execution(* com.akshat.questions.service.*.*(..))")
	public Object createTxn(ProceedingJoinPoint jp) {
		System.out.println("Creating Transaction !!!");
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Object obj = null;
		try {
			obj = jp.proceed();
			
			System.out.println("Committing Transaction !!!");
			tx.commit();		
		} catch (Throwable e) {
			tx.rollback();
			e.printStackTrace();
		}
		return obj;			
	}
}
