package com.app.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Status;
import com.app.pojos.User;
import com.app.pojos.Victim;

@Repository
public class PoliceDao implements IPoliceDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public void fileComplaint(Victim vic, Address addr) 
	{
		sf.getCurrentSession().persist(vic);
		vic.addAddress(addr);
		vic.setStatus(Status.MISSING);
		
	}

	
	  @Override public List<Victim> getAllCases()
	  {
		  String jpql ="select v from Victim v where v.status=:stat";
		  return sf.getCurrentSession().createQuery(jpql,
	  Victim.class).setParameter("stat",Status.MISSING).getResultList();
	  
	  }


	@Override
	public God getVictimByName(String name) 
	{
		String jpql = "select v from Victim v where v.name=:nm";
		 Victim vic = sf.getCurrentSession().createQuery(jpql, Victim.class).setParameter("nm",name).getSingleResult();
		 God god = new God(vic.getName(),vic.getAge(),vic.getGendor(),vic.getHeight(),
				 vic.getBgrp(),vic.getStatus(),vic.getDob(),vic.getMissingDate(),vic.getComplainantNo(),
				 vic.getAddrid().getCity(),vic.getAddrid().getState(),vic.getAddrid().getCountry(),vic.getAddrid().getPhoneno());
		 god.setAppNo(vic.getAppNo());
		 return god;
	}


	@Override
	public Boolean closeCase(Integer appNo)
	{
		try {
			Victim victim = sf.getCurrentSession().get(Victim.class,appNo);
			victim.setStatus(Status.FOUND);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public void updateProfile(Integer usrId, God god) 
	{
		User user = sf.getCurrentSession().get(User.class,usrId);
		if(user != null)
		{
			user.setName(god.getName());
			user.setEmail(god.getEmail());
		}
		String jpql = "select a from Address a where a.userid=:id";
		Address address = sf.getCurrentSession().createQuery(jpql, Address.class).setParameter("id",usrId).getSingleResult();
		if(address != null)
		{
			address.setCity(god.getCity());
			address.setState(god.getState());
			address.setPhoneno(god.getPhoneno());
		}
				
	}
}
