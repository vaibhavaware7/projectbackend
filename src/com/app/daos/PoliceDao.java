package com.app.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Address;
import com.app.pojos.Status;
import com.app.pojos.Victim;

@Repository
public class PoliceDao implements IPoliceDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public void fileComplaint(Victim vic, Address addr) 
	{
		vic.addAddress(addr);
		vic.setStatus(Status.MISSING);
		sf.getCurrentSession().persist(vic);
	}

	
	  @Override public List<Victim> getAllCases() { String jpql =
	  "select v from Victim v"; return sf.getCurrentSession().createQuery(jpql,
	  Victim.class).getResultList();
	  
	  }


	@Override
	public Victim getVictimByName(String name) 
	{
		String jpql = "select v from Victim v where v.name=:nm";
		return sf.getCurrentSession().createQuery(jpql, Victim.class).setParameter("nm",name).getSingleResult();
	}
	 
	
}
