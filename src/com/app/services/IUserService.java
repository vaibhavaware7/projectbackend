package com.app.services;

import java.util.List;

import com.app.pojos.God;
import com.app.pojos.Photo;
import com.app.pojos.User;

public interface IUserService 
{
	User validateUser(String email,String pass);

	void registerUser(God god);

	List<Photo> getAllPhotos();
}
