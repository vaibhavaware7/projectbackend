package com.app.services;

import java.util.List;

import com.app.pojos.God;

public interface INgoService 
{

	List<God> getAllNgoUsers();

	Boolean sendMessage(God god);

}
