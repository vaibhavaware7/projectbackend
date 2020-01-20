package com.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.IPoliceDao;
import com.app.pojos.Address;
import com.app.pojos.Victim;

@Service
@Transactional
public class PoliceService implements IPoliceService
{
	@Autowired
	private IPoliceDao pdao;

	@Override
	public void fileComplaint(Victim vic, Address addr) 
	{
		pdao.fileComplaint(vic,addr);
	}

	@Override
	public List<Victim> getAllCases() 
	{
		return pdao.getAllCases();
	}

	@Override
	public Victim getVictimByName(String name) {
		// TODO Auto-generated method stub
		return pdao.getVictimByName(name);
	}

	
}
