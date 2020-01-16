package com.app.daos;

import com.app.pojos.User;

public interface IUser
{
	User validateUser(String email,String pass);
}
