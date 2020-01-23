package com.app.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.God;
import com.app.pojos.Status;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.pojos.VerificationStatus;
import com.app.services.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController 
{
	@Autowired
	private IUserService serv;
	//private final String responseStatus="ERROR";
	
	@PostConstruct
	public void init()
	{
		System.out.println("in init of User Controller");
	}
	@PostMapping("/login")
	public ResponseEntity<?> getUser(@RequestBody User u)
	{
		try
		{
			User user = serv.validateUser(u.getEmail(),u.getPassword());
			God god = new God(user.getName(),user.getAddId().getCity(),
					user.getAddId().getState(),user.getAddId().getPhoneno(),
					user.getPassword(),user.getUid(),user.getEmail());
			god.setStat(user.getStat());
			god.setRole(user.getRole());
			 return new ResponseEntity<God>(god,HttpStatus.OK);
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
						
		}
			
	}
	@PostMapping("/register")
	public ResponseEntity<Boolean> registerUser(@RequestBody God god)
	{
		try
		{
			serv.registerUser(god);
			 return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
			
						
		}
			
	}

}
