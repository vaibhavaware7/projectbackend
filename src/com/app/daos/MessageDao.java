package com.app.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Message;
import com.app.pojos.User;
@Repository
public class MessageDao implements IMessageDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Message> getAllMessages(Integer usrId) {
		User user = sf.getCurrentSession().get(User.class,usrId);
		 return user.getMessages();
	}

}
