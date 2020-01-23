package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.IAdminDao;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.User;
import com.app.pojos.Victim;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController 
{
	@Autowired
	private IAdminDao dao;
	
	@GetMapping("/user/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable String name)
	{
		try {
			 God god = dao.getUserByName(name);
			return new ResponseEntity<God>(god,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/verify/{uid}")
	public ResponseEntity<Boolean> verifyUser(@PathVariable Integer uid)
	{
		try {
			 dao.verifyUser(uid);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/requests")
	public ResponseEntity<?> getAllRequests()
	{
		try {
			List<User> users= dao.getAllRequests();
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@PostMapping("/addept")
	public ResponseEntity<Boolean> addDept(@RequestBody God god)
	{
		try {
			 dao.addDept(god);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/msgs/{uid}")
	public ResponseEntity<?> getAdminMessages(@PathVariable Integer uid)
	{
		try {
			List<Message> msgs= dao.getAdminMessages(uid);
			return new ResponseEntity<List<Message>>(msgs,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers()
	{
		try {
			List<User> users= dao.getAllUsers();
			return new ResponseEntity<List<User>>(users,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/cases")
	public ResponseEntity<?> getAllCases()
	{
		try {
			List<Victim> victims= dao.getAllCases();
			return new ResponseEntity<List<Victim>>(victims,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/remove/{appNo}")
	public ResponseEntity<Boolean> removeCase(@PathVariable Integer appNo)
	{
		try {
			 dao.removeCase(appNo);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<Boolean> adduser(@RequestBody God god)
	{
		try {
			 dao.addUser(god);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/getvic/{name}")
	public ResponseEntity<?> getVictimByName(@PathVariable String name)
	{
		try {
			 God god = dao.getVictimByName(name);
			return new ResponseEntity<God>(god,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Integer uid)
	{
			try {
				dao.deleteUser(uid);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
				
			}
				
			
	}
	
}
