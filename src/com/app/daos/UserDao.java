package com.app.daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.God;
import com.app.pojos.User;

@Repository
public class UserDao implements IUser
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public User validateUser(String email, String pass) 
	{	
		String jpql="select u from User u where u.email=:em and u.password=:pas";
		User user = sf.getCurrentSession().createQuery(jpql, User.class)
				.setParameter("em",email).setParameter("pas",pass).getSingleResult();	
		return user;
				
	}

}
