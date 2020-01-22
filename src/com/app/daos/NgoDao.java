package com.app.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.User;
import com.app.pojos.UserRole;

@Repository
public class NgoDao implements INgoDao 
{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<God> getAllNgoUsers() {
		String jpql = "select u from User u where u.role=:ro";
		List<User> list = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("ro",UserRole.NGO).getResultList();
		List<God> godlist =new ArrayList<God>();
		if(list.size() != 0)
		{
			for (User u : list)
			{
				God god = new God();
				god.setNgoId(u.getNgoid().getNgoId());
				god.setNgoname(u.getNgoid().getNgoname());
				god.setName(u.getName());
				god.setEmail(u.getEmail());
				godlist.add(god);
			}
			
	
		}
		return godlist;
	}

	@Override
	public Boolean sendMessage(God god)
	{
		try {
			String jpql = "select u from User u where u.email=:em";
			User user = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("em",god.getNgouseremail()).getSingleResult();
			if(user != null)
			{
				String message = "From : "+god.getFromuseremail()+" \n "+" Message : "+god.getMessagebody();
				Message msg = new Message();
				msg.setMsg(message);
				sf.getCurrentSession().persist(msg);
				user.addMessage(msg);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
