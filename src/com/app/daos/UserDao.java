package com.app.daos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Ngo;
import com.app.pojos.Photo;
import com.app.pojos.PhotoOwner;
import com.app.pojos.Police;
import com.app.pojos.Status;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.pojos.VerificationStatus;
import com.app.pojos.Victim;

@Repository
public class UserDao implements IUser
{
	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private JavaMailSender sender;
	
	public Integer otp;
	@Override
	public User validateUser(String email, String pass) 
	{	
		String jpql="select u from User u where u.email=:em and u.password=:pas";
		User user = sf.getCurrentSession().createQuery(jpql, User.class)
				.setParameter("em",email).setParameter("pas",pass).getSingleResult();	
		return user;
				
	}

	@Override
	public void registerUser(God god, MultipartFile image) throws IOException 
	{
		if(god.getOtp().equals(this.otp))
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
				
				Photo pho = new Photo();
				pho.setImg(image.getBytes());
				pho.setOwner(PhotoOwner.USER);
				sf.getCurrentSession().persist(pho);
				user.addPhoto(pho);
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
				Photo pho = new Photo();
				pho.setImg(image.getBytes());
				pho.setOwner(PhotoOwner.USER);
				sf.getCurrentSession().persist(pho);
				user.addPhoto(pho);		
				
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
	}

	@Override
	public List<Photo> getAllPhotos() 
	{
		String jpql = "select p from Photo p where p.owner=:ow";
		return sf.getCurrentSession().createQuery(jpql,Photo.class).setParameter("ow",PhotoOwner.VICTIM).getResultList();
	}

	@Override
	public Integer[] getDatapoints()
	{
		String jpql ="select u from Victim u where u.status=:st";
		List<Victim> resultList = sf.getCurrentSession().createQuery(jpql, Victim.class).setParameter("st",Status.MISSING).getResultList();
		int count1 = resultList.size();
		
		String jpql1 ="select u from Victim u where u.status=:st";
		List<Victim> resultList1 = sf.getCurrentSession().createQuery(jpql1, Victim.class).setParameter("st",Status.FOUND).getResultList();
		int count2 = resultList1.size();
		Integer[] arr = new Integer[2];
		arr[0]=count1;
		arr[1]=count2;
		return arr;

	}

	@Override
	public void generateOtp(String email) 
	{
		Integer otp1 = (int) ((Math.random()*1000)+2379);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email+".com");
		msg.setSubject("OTP For Registration");
		String msgText= "Your OTP For Registration is "+ otp1.toString();
		msg.setText(msgText);
		sender.send(msg);
		this.otp=otp1;
		
	}

}
