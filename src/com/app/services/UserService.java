package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.IUser;
import com.app.pojos.God;
import com.app.pojos.User;

@Service
@Transactional
public class UserService implements IUserService 
{
	@Autowired
	private IUser dao;
	
	public User validateUser(String email,String pass)
	{
		return dao.validateUser(email, pass);
	}
}
