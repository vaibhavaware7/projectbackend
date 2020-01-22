package com.app.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.User;
import com.app.pojos.Victim;

@Repository
@Transactional
public class AdminDao implements IAdminDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<User> getAllUsers() 
	{
		String jpql = "select u from User u";
		return sf.getCurrentSession().createQuery(jpql, User.class).getResultList();
		
	}

	@Override
	public List<Victim> getAllCases()
	{
		String jpql = "select v from Victim v";
		
		return sf.getCurrentSession().createQuery(jpql, Victim.class).getResultList();
	}

	@Override
	public void removeCase(Integer appNo) 
	{
		Victim vic = sf.getCurrentSession().get(Victim.class,appNo);
		sf.getCurrentSession().delete(vic);
	}

}
