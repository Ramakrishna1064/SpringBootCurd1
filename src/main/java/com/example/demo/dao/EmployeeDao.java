package com.example.demo.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

//	@Autowired
//	SessionFactory sessionFactory;
//	
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//	
//	
//	/**
//	 * getListOfStates
//	 * 
//	 * @return
//	 */
//	public List<?> getListOfStates(int userId) {
//        System.out.println("getListOfStates"+userId);
//		String hql = "FROM StatesDTO s WHERE " + "s.userid = :userId";
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery(hql);
//		query.setParameter("userId", userId);
//		session.flush();
//		session.clear();
//		return query.list();
//	}

}
