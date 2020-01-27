package com.app.daos;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Victim;

public interface IPoliceDao {

	void fileComplaint(Victim vic, Address addr, MultipartFile image) throws IOException;

	List<God> getAllCases();

	God getVictimByName(String name);

	Boolean closeCase(Integer appNo);

	void updateProfile(God god);
	
}
