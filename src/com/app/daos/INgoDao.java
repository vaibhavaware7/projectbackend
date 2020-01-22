package com.app.daos;

import java.util.List;

import com.app.pojos.God;

public interface INgoDao 
{

	List<God> getAllNgoUsers();

	Boolean sendMessage(God god);

}
