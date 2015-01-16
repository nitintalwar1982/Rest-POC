package com.sports.nex.dao.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.sports.nex.dao.UserDao;
import com.sports.nex.entities.Users;

public class UserDaoImpl implements UserDao{

	@PersistenceContext(unitName="nexPersistence")
	private EntityManager entityManager;
	 
    	
	@Override
	@Transactional
	public String save(Users p) {
		entityManager.persist(p);
		entityManager.flush();		
		return p.getUserEmailAddress();		
	}

	@Override
	public List<Users> list() {
			
			String qlString = "SELECT p FROM Users p";
			TypedQuery<Users> query = entityManager.createQuery(qlString, Users.class);
			return query.getResultList();
	}

}
