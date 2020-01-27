package com.app.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Ngo;
import com.app.pojos.Photo;
import com.app.pojos.Police;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.pojos.VerificationStatus;

@Repository
public class UserDao implements IUser
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public User validateUser(String email, String pass) 
	{	
		String jpql="select u from User u where u.email=:em and u.password=:pas";
		User user = sf.getCurrentSession().createQuery(jpql, User.class)
				.setParameter("em",email).setParameter("pas",pass).getSingleResult();	
		return user;
				
	}

	@Override
	public void registerUser(God god) 
	{
		if(god.getRole().equals(UserRole.POLICE))
		{
			User user = new User(god.getName(),god.getEmail(),god.getPassword(),god.getRole());
			user.setStat(VerificationStatus.NV);
			sf.getCurrentSession().persist(user);
			Address usraddr = new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
			sf.getCurrentSession().persist(usraddr);
			user.addAddress(usraddr);
			
			Police pol = new Police(god.getDeptname());
			sf.getCurrentSession().persist(pol);
			user.addPoliceStation(pol);
			
			Address poaddr = new Address(god.getDeptcity(),god.getDeptstate(),
					god.getDeptcountry(),god.getDeptphoneno());
			sf.getCurrentSession().persist(poaddr);
			
			pol.addAddress(poaddr);			
		}
		else if(god.getRole().equals(UserRole.NGO))
		{
			User user = new User(god.getName(),god.getEmail(),god.getPassword(),god.getRole());
			user.setStat(VerificationStatus.NV);
			sf.getCurrentSession().persist(user);
			Address usraddr = new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
			sf.getCurrentSession().persist(usraddr);
			user.addAddress(usraddr);
		
			Ngo ngo = new Ngo(god.getDeptname());
			sf.getCurrentSession().persist(ngo);
			user.addNgoOfUser(ngo);
			
			Address ngoaddr = new Address(god.getDeptcity(),god.getDeptstate(),
					god.getDeptcountry(),god.getDeptphoneno());
			sf.getCurrentSession().persist(ngoaddr);
			
			ngo.addAddress(ngoaddr);		
			
		}
		else
		{
			User user = new User(god.getName(),god.getEmail(),god.getPassword(),god.getRole());
			user.setStat(VerificationStatus.V);
			sf.getCurrentSession().persist(user);
			Address usraddr = new Address(god.getCity(),god.getState(),god.getCountry(),god.getPhoneno());
			sf.getCurrentSession().persist(usraddr);
			user.addAddress(usraddr);
		
		}
	}

	@Override
	public List<Photo> getAllPhotos() 
	{
		String jpql = "select p from Photo p";
		return sf.getCurrentSession().createQuery(jpql,Photo.class).getResultList();
	}

}
