package com.app.daos;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.God;
import com.app.pojos.Photo;
import com.app.pojos.User;

public interface IUser
{
	User validateUser(String email,String pass);

	void registerUser(God god, MultipartFile image)throws IOException;

	List<Photo> getAllPhotos();
	
	Integer[] getDatapoints();
}
