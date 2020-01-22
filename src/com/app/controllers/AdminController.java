package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.IAdminDao;
import com.app.pojos.User;
import com.app.pojos.Victim;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController 
{
	@Autowired
	private IAdminDao dao;
	
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
}
