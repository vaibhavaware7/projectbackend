package com.app.daos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Photo;
import com.app.pojos.Status;
import com.app.pojos.User;
import com.app.pojos.Victim;

@Repository
public class PoliceDao implements IPoliceDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public void fileComplaint(Victim vic, Address addr ,MultipartFile image) throws IOException 
	{
		sf.getCurrentSession().persist(vic);
		vic.addAddress(addr);
		vic.setStatus(Status.MISSING);
		
		Photo pho = new Photo();
		pho.setImg(image.getBytes());
		sf.getCurrentSession().persist(pho);
		vic.addPhoto(pho);
		
	}

	
	  @Override public List<God> getAllCases()
	  {
		  String jpql ="select v from Victim v where v.status=:stat";
		   List<Victim> list = sf.getCurrentSession().createQuery(jpql,
	  Victim.class).setParameter("stat",Status.MISSING).getResultList();
		   List<God> godlist= new ArrayList<God>();
		   for (Victim victim : list)
		   {
			   God god= new God(victim.getAppNo(),victim.getName(),victim.getAge(), victim.getGendor(),victim.getHeight(),victim.getBgrp(),victim.getDob(),victim.getMissingDate(),victim.getComplainantNo(),victim.getAddrid().getCity(),victim.getAddrid().getState(),victim.getAddrid().getCountry(),
					   victim.getAddrid().getPhoneno(),victim.getPhoId().getImg());
			   god.setStatus(victim.getStatus());
			   godlist.add(god);
		   }
		   return godlist;
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
		 god.setImg(vic.getPhoId().getImg());
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
	public void updateProfile( God god) 
	{
		User user = sf.getCurrentSession().get(User.class,god.getUid());
		if(user != null)
		{
			user.setName(god.getName());
			user.setEmail(god.getEmail());
			user.getAddId().setCity(god.getCity());
			user.getAddId().setCountry(god.getCountry());
			user.getAddId().setPhoneno(god.getPhoneno());
		}
		
		/*
		 * String jpql = "select a from Address a where a.userid=:id"; Address address =
		 * sf.getCurrentSession().createQuery(jpql,
		 * Address.class).setParameter("id",user.getUid()).getSingleResult(); if(address
		 * != null) { address.setCity(god.getCity()); address.setState(god.getState());
		 * address.setPhoneno(god.getPhoneno()); }
		 */
				
	}
}
