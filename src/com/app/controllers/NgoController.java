package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.God;
import com.app.services.INgoService;

@RestController
@RequestMapping("/ngo")
@CrossOrigin
public class NgoController 
{
	@Autowired
	private INgoService service;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllNgoUsers()
	{
		try {
			List<God> ngoUsers = service.getAllNgoUsers();
			return new ResponseEntity<List<God>>(ngoUsers,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/send")
	public ResponseEntity<Boolean> sendMessage(@RequestBody God god)
	{
		Boolean stat = service.sendMessage(god);
		if(stat == true)
			return new ResponseEntity<Boolean>(stat,HttpStatus.OK);
		return new ResponseEntity<Boolean>(stat,HttpStatus.NO_CONTENT);
		}
}
