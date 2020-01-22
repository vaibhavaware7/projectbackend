package com.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.IMessageDao;
import com.app.daos.IPoliceDao;
import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.Victim;

@Service
@Transactional
public class PoliceService implements IPoliceService
{
	@Autowired
	private IPoliceDao pdao;
	@Autowired
	private IMessageDao mdao;

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
	public God getVictimByName(String name) {
		// TODO Auto-generated method stub
		return pdao.getVictimByName(name);
	}

	@Override
	public Address getAddressOfVictim(Victim vic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getAllMessages(Integer usrId)
	{
		return mdao.getAllMessages(usrId);
	}

	
}
