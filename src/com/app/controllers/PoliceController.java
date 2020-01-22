package com.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.IPoliceDao;
import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.Victim;
import com.app.services.IPoliceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/police")
@CrossOrigin
public class PoliceController 
{
	@Autowired
	private IPoliceService pserv;
	
	
	@PostMapping("/filecomplaint")
	public ResponseEntity<Boolean> fileComplaint(@RequestBody God god )
	{
		try {
			Victim victim = new Victim(god.getName(),god.getAge(),god.getGendor(),
							god.getHeight(),god.getBgrp(),god.getDob(),god.getMissingDate(),
							god.getComplainantNo());
			Address addr = new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
			pserv.fileComplaint(victim,addr);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@GetMapping("/allcases")
	public ResponseEntity<?> getAllCases()
	{
		List<Victim> cases = pserv.getAllCases();
		if(cases.size() == 0)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Victim>>(cases, HttpStatus.OK);
	}
	@GetMapping("/cases/{name}")
	public ResponseEntity<?> getVictimByName(@PathVariable String name)
	{
		try {
			 God god = pserv.getVictimByName(name);
			 	
				return new ResponseEntity<God>(god, HttpStatus.OK);
									
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}
	@GetMapping("/msgs/{usrId}")
	public ResponseEntity<?> getAllMessages(@PathVariable Integer usrId)
	{
		try 
		{
			List<Message> msglist = pserv.getAllMessages(usrId);
			return new ResponseEntity<List<Message>>(msglist,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();

			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
}
