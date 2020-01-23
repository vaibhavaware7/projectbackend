package com.app.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.Ngo;
import com.app.pojos.Police;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.pojos.VerificationStatus;
import com.app.pojos.Victim;

@Repository
@Transactional
public class AdminDao implements IAdminDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<User> getAllUsers() 
	{
		String jpql = "select u from User u where u.stat=:st";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("st",VerificationStatus.V).getResultList();
		
	}

	@Override
	public List<Victim> getAllCases()
	{
		String jpql = "select v from Victim v";
		
		return sf.getCurrentSession().createQuery(jpql, Victim.class).getResultList();
	}

	@Override
	public void removeCase(Integer appNo) 
	{
		Victim vic = sf.getCurrentSession().get(Victim.class,appNo);
		sf.getCurrentSession().delete(vic);
	}

	@Override
	public void addUser(God god) 
	{
		User u = new User(god.getName(),god.getEmail(),god.getPassword(),god.getRole());
		u.setStat(VerificationStatus.V);
		sf.getCurrentSession().persist(u);
		
		Address adr =new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
		sf.getCurrentSession().persist(adr);
		
		u.addAddress(adr);
	}

	@Override
	public God getVictimByName(String name)
	{
		String jpql= "select v from Victim v where v.name=:na";
		Victim vic = sf.getCurrentSession().createQuery(jpql,Victim.class).setParameter("na",name).getSingleResult();
		God god = new God(vic.getName(),vic.getAge(),vic.getGendor(),vic.getHeight(),
				vic.getBgrp(),vic.getStatus(),vic.getDob(),vic.getMissingDate(),
				vic.getComplainantNo(),vic.getAddrid().getCity(),vic.getAddrid().getState(),
				vic.getAddrid().getCountry(),vic.getAddrid().getPhoneno());  
		god.setAppNo(vic.getAppNo());
		return god;
	}

	@Override
	public List<Message> getAdminMessages(Integer uid)
	{
		User user = sf.getCurrentSession().get(User.class,uid);
	//	String jpql = "select m from Message m where m.usrId =:id ";
		//List<Message> list = sf.getCurrentSession().createQuery(jpql, Message.class).setParameter("id",uid).getResultList();
		return user.getMessages();
	}

	@Override
	public void deleteUser(Integer uid) 
	{
		User user = sf.getCurrentSession().get(User.class,uid);
		user.setStat(VerificationStatus.NV);
		
	}
	
	@Override
	public void addDept(God god) 
	{
		if(god.getRole().equals(UserRole.NGO))
		{
			Ngo ngo = new Ngo(god.getName());
			sf.getCurrentSession().persist(ngo);
			Address addr =new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
			sf.getCurrentSession().persist(addr);
			ngo.addAddress(addr);
			
		}
		else 
		{
			Police police = new Police(god.getName());
			sf.getCurrentSession().persist(police);
			Address addr =new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
			sf.getCurrentSession().persist(addr);
			police.addAddress(addr);
			
		}
		
		
	}

}
