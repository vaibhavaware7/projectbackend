package com.app.daos;

import java.text.ParseException;
import java.util.List;

import com.app.pojos.God;
import com.app.pojos.Photo;
import com.app.pojos.User;

public interface IUser
{
	User validateUser(String email,String pass);

	void registerUser(God god);

	List<Photo> getAllPhotos();
	
	Integer[] getDatapoints();
}
