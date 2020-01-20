package com.app.services;

import java.util.List;
import java.util.Map;

import com.app.pojos.Address;
import com.app.pojos.Victim;

public interface IPoliceService {

	void fileComplaint(Victim vic, Address addr);

	List<Victim> getAllCases();

	Victim getVictimByName(String name);
	
}
