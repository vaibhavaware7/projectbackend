package com.app.daos;

import java.util.List;

import com.app.pojos.User;
import com.app.pojos.Victim;

public interface IAdminDao 
{

	List<User> getAllUsers();

	List<Victim> getAllCases();

	void removeCase(Integer appNo);

}
