package com.app.daos;

import com.app.pojos.God;
import com.app.pojos.User;

public interface IUser
{
	User validateUser(String email,String pass);

	void registerUser(God god);
}
