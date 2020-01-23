package com.app.daos;

import java.util.List;

import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.User;
import com.app.pojos.Victim;

public interface IAdminDao 
{

	List<User> getAllUsers();

	List<Victim> getAllCases();

	void removeCase(Integer appNo);

	void addUser(God god);

	God getVictimByName(String name);

	List<Message> getAdminMessages(Integer uid);

	void deleteUser(Integer uid);

	void addDept(God god);

	List<User> getAllRequests();

	void verifyUser(Integer uid);

	God getUserByName(String name);

}
