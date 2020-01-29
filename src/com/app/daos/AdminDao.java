package com.app.daos;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.God;
import com.app.pojos.Message;
import com.app.pojos.Ngo;
import com.app.pojos.Photo;
import com.app.pojos.Police;
import com.app.pojos.User;
import com.app.pojos.UserRole;
import com.app.pojos.VerificationStatus;
import com.app.pojos.Victim;

@Repository
@Transactional
public class AdminDao implements IAdminDao {
	@Autowired
	private SessionFactory sf;

	@Override
	public List<User> getAllUsers() {
		String jpql = "select u from User u where u.stat=:st";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("st", VerificationStatus.V)
				.getResultList();

	}

	@Override
	public List<Victim> getAllCases() {
		String jpql = "select v from Victim v";

		return sf.getCurrentSession().createQuery(jpql, Victim.class).getResultList();
	}

	@Override
	public void removeCase(Integer appNo) {
		Victim vic = sf.getCurrentSession().get(Victim.class, appNo);
		sf.getCurrentSession().delete(vic.getAddrid());
		vic.removeAddress(vic.getAddrid());
		sf.getCurrentSession().delete(vic.getPhoId());
		vic.removePhoto(vic.getPhoId());
		
		sf.getCurrentSession().delete(vic);
	}

	@Override
	public void addUser(God god) {
		User u = new User(god.getName(), god.getEmail(), god.getPassword(), god.getRole());
		u.setStat(VerificationStatus.V);
		sf.getCurrentSession().persist(u);

		Address adr = new Address(god.getCity(), god.getState(), god.getCountry(), god.getPhoneno());
		sf.getCurrentSession().persist(adr);

		u.addAddress(adr);
	}

	@Override
	public God getVictimByName(String name) {
		String jpql = "select v from Victim v where v.name=:na";
		Victim vic = sf.getCurrentSession().createQuery(jpql, Victim.class).setParameter("na", name).getSingleResult();
		God god = new God(vic.getName(), vic.getAge(), vic.getGendor(), vic.getHeight(), vic.getBgrp(), vic.getStatus(),
				vic.getDob(), vic.getMissingDate(), vic.getComplainantNo(), vic.getAddrid().getCity(),
				vic.getAddrid().getState(), vic.getAddrid().getCountry(), vic.getAddrid().getPhoneno());
		god.setAppNo(vic.getAppNo());
		return god;
	}

	@Override
	public List<Message> getAdminMessages(Integer uid) {
		User user = sf.getCurrentSession().get(User.class, uid);
		// String jpql = "select m from Message m where m.usrId =:id ";
		// List<Message> list = sf.getCurrentSession().createQuery(jpql,
		// Message.class).setParameter("id",uid).getResultList();
		return user.getMessages();
	}

	@Override
	public void deleteUser(Integer uid) {
		User user = sf.getCurrentSession().get(User.class, uid);
		user.setStat(VerificationStatus.NV);

	}

	@Override
	public void addDept(God god) {
		if (god.getRole().equals(UserRole.NGO)) {
			Ngo ngo = new Ngo(god.getName());
			sf.getCurrentSession().persist(ngo);
			Address addr = new Address(god.getCity(), god.getState(), god.getCountry(), god.getPhoneno());
			sf.getCurrentSession().persist(addr);
			ngo.addAddress(addr);

		} else {
			Police police = new Police(god.getName());
			sf.getCurrentSession().persist(police);
			Address addr = new Address(god.getCity(), god.getState(), god.getCountry(), god.getPhoneno());
			sf.getCurrentSession().persist(addr);
			police.addAddress(addr);

		}

	}

	@Override
	public List<User> getAllRequests() {
		String jpql = "select u from User u where u.stat=:st";

		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("st", VerificationStatus.NV)
				.getResultList();
	}

	@Override
	public void verifyUser(Integer uid) {
		User user = sf.getCurrentSession().get(User.class, uid);
		user.setStat(VerificationStatus.V);
	}

	@Override
	public God getUserByName(String name) {
		String jpql = "select u from User u where u.name=:nm";
		User user = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("nm", name).getSingleResult();
		if (user.getRole().equals(UserRole.NGO)) {
			God god = new God(user.getName(), user.getAddId().getCity(), user.getAddId().getState(),
					user.getAddId().getCountry(), user.getAddId().getPhoneno(), user.getStat(), user.getUid(),
					user.getEmail(), user.getNgoid().getNgoname(), user.getNgoid().getNgoAddrId().getCity(),
					user.getNgoid().getNgoAddrId().getState(), user.getNgoid().getNgoAddrId().getCountry(),
					user.getNgoid().getNgoAddrId().getPhoneno());
			god.setRole(UserRole.NGO);

			return god;
		} else if (user.getRole().equals(UserRole.POLICE)) {
			God god = new God(user.getName(), user.getAddId().getCity(), user.getAddId().getState(),
					user.getAddId().getCountry(), user.getAddId().getPhoneno(), user.getStat(), user.getUid(),
					user.getEmail(), user.getPoId().getName(), user.getPoId().getPoaddrId().getCity(),
					user.getPoId().getPoaddrId().getState(), user.getPoId().getPoaddrId().getCountry(),
					user.getPoId().getPoaddrId().getPhoneno());
			god.setRole(UserRole.POLICE);

			return god;

		} else {
			God god = new God();
			god.setName(user.getName());
			god.setEmail(user.getEmail());
			god.setRole(UserRole.ADMIN);
			god.setCity(user.getAddId().getCity());
			god.setState(user.getAddId().getState());
			god.setPhoneno(user.getAddId().getPhoneno());
			return god;
		}
	}

	@Override
	public void removeUser(Integer uid) {
		// User user = sf.getCurrentSession().get(User.class,uid);
		String jpql0 = "select u from User u where u.uid=:userid";
		User user = sf.getCurrentSession().createQuery(jpql0, User.class).setParameter("userid", uid).getSingleResult();
		/*
		 * String jpql="select a from Address a where a.userid=:id"; Address address =
		 * sf.getCurrentSession().createQuery(jpql,
		 * Address.class).setParameter("id",uid).getSingleResult();
		 */

		sf.getCurrentSession().delete(user.getAddId());
		user.removeAddress(user.getAddId());

		if (user.getRole().equals(
				UserRole.POLICE)) { /*
									 * String jpql2="select p from Police p where p.pouserid=:id"; Police police =
									 * sf.getCurrentSession().createQuery(jpql2,
									 * Police.class).setParameter("id",uid).getSingleResult(); String
									 * jpql3="select a from Address a where a.polId=:id"; Address addresspo =
									 * sf.getCurrentSession().createQuery(jpql3,
									 * Address.class).setParameter("id",police.getDeptId()).getSingleResult();
									 * police.removeAddress(addresspo); sf.getCurrentSession().delete(addresspo);
									 * user.removePolice(police); sf.getCurrentSession().delete(police);
									 * sf.getCurrentSession().delete(user);
									 */
			Police police = sf.getCurrentSession().get(Police.class,user.getPoId().getDeptId());
			sf.getCurrentSession().delete(user.getPoId().getPoaddrId());
			police.removeAddress(police.getPoaddrId());
			sf.getCurrentSession().delete(police);
			user.removePolice(user.getPoId());
			Photo photo = sf.getCurrentSession().get(Photo.class,user.getUsrPhoto().getpId());
			sf.getCurrentSession().delete(photo);
			user.removePhoto(user.getUsrPhoto());
			sf.getCurrentSession().delete(user);

		} else if (user.getRole().equals(UserRole.NGO)) {
			/*
			 * String jpql2="select n from Ngo n where n.userid=:id"; Ngo ngo
			 * =sf.getCurrentSession().createQuery(jpql2,
			 * Ngo.class).setParameter("id",user.getUid()).getSingleResult(); String
			 * jpql3="select a from Address a where a.ngoKey=:id"; Address addresspo =
			 * sf.getCurrentSession().createQuery(jpql3,
			 * Address.class).setParameter("id",ngo.getNgoId()).getSingleResult();
			 * ngo.removeAddress(addresspo); sf.getCurrentSession().delete(addresspo);
			 * user.removeNgo(ngo); sf.getCurrentSession().delete(ngo);
			 * 
			 * sf.getCurrentSession().delete(user); } else {
			 * 
			 * sf.getCurrentSession().delete(user); }
			 */
			Ngo ngo = sf.getCurrentSession().get(Ngo.class,user.getNgoid().getNgoId());
			sf.getCurrentSession().delete(user.getNgoid().getNgoAddrId());
			ngo.removeAddress(ngo.getNgoAddrId());
			sf.getCurrentSession().delete(ngo);
			user.removeNgo(user.getNgoid());

			Photo photo = sf.getCurrentSession().get(Photo.class,user.getUsrPhoto().getpId());
			sf.getCurrentSession().delete(photo);
			user.removePhoto(user.getUsrPhoto());
			sf.getCurrentSession().delete(user);
			
			}
		else
		{
			sf.getCurrentSession().delete(user);
			
		}
	}

	@Override
	public God getUserByEmail(String email) {
		String jpql = "select u from User u where u.email=:em";
		User user = sf.getCurrentSession().createQuery(jpql, User.class).setParameter("em", email).getSingleResult();
		if (user.getRole().equals(UserRole.NGO)) {
			God god = new God(user.getName(), user.getAddId().getCity(), user.getAddId().getState(),
					user.getAddId().getCountry(), user.getAddId().getPhoneno(), user.getStat(), user.getUid(),
					user.getEmail(), user.getNgoid().getNgoname(), user.getNgoid().getNgoAddrId().getCity(),
					user.getNgoid().getNgoAddrId().getState(), user.getNgoid().getNgoAddrId().getCountry(),
					user.getNgoid().getNgoAddrId().getPhoneno());
			god.setRole(UserRole.NGO);
			
			return god;
		} else if (user.getRole().equals(UserRole.POLICE)) {
			God god = new God(user.getName(), user.getAddId().getCity(), user.getAddId().getState(),
					user.getAddId().getCountry(), user.getAddId().getPhoneno(), user.getStat(), user.getUid(),
					user.getEmail(), user.getPoId().getName(), user.getPoId().getPoaddrId().getCity(),
					user.getPoId().getPoaddrId().getState(), user.getPoId().getPoaddrId().getCountry(),
					user.getPoId().getPoaddrId().getPhoneno());
			god.setRole(UserRole.POLICE);
			
			return god;

		} else {
			God god = new God();
			god.setName(user.getName());
			god.setEmail(user.getEmail());
			god.setRole(UserRole.ADMIN);
			god.setCity(user.getAddId().getCity());
			god.setState(user.getAddId().getState());
			god.setPhoneno(user.getAddId().getPhoneno());
			
			return god;
		}

	}

	@Override
	public God getUserById(Integer uid) {
		User user = sf.getCurrentSession().get(User.class, uid);
		if (user.getRole().equals(UserRole.NGO)) {
			God god = new God(user.getName(), user.getAddId().getCity(), user.getAddId().getState(),
					user.getAddId().getCountry(), user.getAddId().getPhoneno(), user.getStat(), user.getUid(),
					user.getEmail(), user.getNgoid().getNgoname(), user.getNgoid().getNgoAddrId().getCity(),
					user.getNgoid().getNgoAddrId().getState(), user.getNgoid().getNgoAddrId().getCountry(),
					user.getNgoid().getNgoAddrId().getPhoneno());
			god.setRole(UserRole.NGO);
			god.setImg(user.getUsrPhoto().getImg());
			return god;
		} else if (user.getRole().equals(UserRole.POLICE)) {
			God god = new God(user.getName(), user.getAddId().getCity(), user.getAddId().getState(),
					user.getAddId().getCountry(), user.getAddId().getPhoneno(), user.getStat(), user.getUid(),
					user.getEmail(), user.getPoId().getName(), user.getPoId().getPoaddrId().getCity(),
					user.getPoId().getPoaddrId().getState(), user.getPoId().getPoaddrId().getCountry(),
					user.getPoId().getPoaddrId().getPhoneno());
			god.setRole(UserRole.POLICE);

			god.setImg(user.getUsrPhoto().getImg());
			return god;

		} else {
			God god = new God();
			god.setName(user.getName());
			god.setEmail(user.getEmail());
			god.setRole(UserRole.ADMIN);
			god.setCity(user.getAddId().getCity());
			god.setState(user.getAddId().getState());
			god.setPhoneno(user.getAddId().getPhoneno());

			god.setImg(user.getUsrPhoto().getImg());
			return god;
		}
	}
}
