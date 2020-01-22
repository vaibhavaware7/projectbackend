package com.app.services;

import java.util.List;
import java.util.Map;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.Victim;

public interface IPoliceService {

	void fileComplaint(Victim vic, Address addr);

	List<Victim> getAllCases();

	God getVictimByName(String name);

	Address getAddressOfVictim(Victim vic);

	List<Message> getAllMessages(Integer usrId);
	
}
