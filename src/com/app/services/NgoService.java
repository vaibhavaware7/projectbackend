package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.INgoDao;
import com.app.pojos.God;
import com.app.pojos.User;

@Service
@Transactional
public class NgoService implements INgoService 
{
	@Autowired
	private INgoDao dao;
	
	@Override
	public List<God> getAllNgoUsers()
	{
		
		return dao.getAllNgoUsers();
	}

	@Override
	public Boolean sendMessage(God god) {
		// TODO Auto-generated method stub
		return dao.sendMessage(god);
	}

}
