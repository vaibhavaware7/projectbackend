package com.app.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.Victim;

public interface IPoliceService {

	void fileComplaint(Victim vic, Address addr, MultipartFile image) throws IOException;

	List<God> getAllCases();

	God getVictimByName(String name);

	Address getAddressOfVictim(Victim vic);

	List<Message> getAllMessages(Integer usrId);

	Boolean closeCase(Integer appNo);

	void updateUser(God god);
	
}
