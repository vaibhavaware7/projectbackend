package com.app.services;

import com.app.pojos.User;

public interface IUserService 
{
	User validateUser(String email,String pass);
}
