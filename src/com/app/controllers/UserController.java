package com.app.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.User;
import com.app.services.IUserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private IUserService serv;
	
	@PostConstruct
	public void init()
	{
		System.out.println("in init of User Controller");
	}
	@PostMapping("/login")
	public ResponseEntity<?> getUser(@RequestBody User u)
	{
		User user = serv.validateUser(u.getEmail(),u.getPassword());
		if(user != null)
				return new ResponseEntity<User>(user,HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
}
