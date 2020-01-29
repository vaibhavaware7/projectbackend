package com.app.controllers;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.God;
import com.app.pojos.Photo;
import com.app.pojos.User;
import com.app.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@GetMapping("/imgs")
	public ResponseEntity<?> getAllPhotos()
	{
		try {
			List<Photo> photos= serv.getAllPhotos();
			return new ResponseEntity<List<Photo>>(photos,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
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
	public ResponseEntity<Boolean> registerUser(@RequestParam(value="god") String god1,
			@RequestParam(value="image") MultipartFile image )
	{
		try
		{
			God god = new ObjectMapper().readValue(god1,God.class);
			
			serv.registerUser(god,image);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
			
						
		}
			
	}
	@GetMapping("/stat")
	public ResponseEntity<?> getStat()
	{
		try {
		Integer[] datapoints = serv.getDatapoints();
			return new ResponseEntity<Integer[]>(datapoints, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return new ResponseEntity<>( HttpStatus.OK);
		}
	}
	@GetMapping("/generate/{email}")
	public ResponseEntity<?> generateOtp(@PathVariable String email)
	{
		try {
			 serv.generateOtp(email);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
	}
}
