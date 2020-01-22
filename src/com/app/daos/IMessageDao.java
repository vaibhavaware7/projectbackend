package com.app.daos;

import java.util.List;

import com.app.pojos.Message;

public interface IMessageDao
{

	List<Message> getAllMessages(Integer usrId);

}
