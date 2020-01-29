package com.app.pojos;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "victims")
public class Victim 
{
	private Integer appNo;
	private String name;
	private Integer age;
	private Gendor gendor;
	private Double height;
	private String bgrp;
	private Status status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date missingDate;
	private String complainantNo;
	@JsonIgnore
	private Address addrid;
	@JsonIgnore
	private Photo phoId;
	
	public Victim() {
		System.out.println("In victim ctor");
	}

	public Victim(String name, Integer age, Gendor gendor, Double height, String bgrp, Date dob, Date missingDate,
			String complainantNo) {
		super();
		this.name = name;
		this.age = age;
		this.gendor = gendor;
		this.height = height;
		this.bgrp = bgrp;
		this.dob = dob;
		this.missingDate = missingDate;
		this.complainantNo = complainantNo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_no")
	public Integer getAppNo() {
		return appNo;
	}

	public void setAppNo(Integer appNo) {
		this.appNo = appNo;
	}
	@Column(length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	public Gendor getGendor() {
		return gendor;
	}

	public void setGendor(Gendor gendor) {
		this.gendor = gendor;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Column(length = 10)
	public String getBgrp() {
		return bgrp;
	}

	public void setBgrp(String bgrp) {
		this.bgrp = bgrp;
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Temporal(TemporalType.DATE)
	public Date getMissingDate() {
		return missingDate;
	}

	public void setMissingDate(Date missingDate) {
		this.missingDate = missingDate;
	}
	@Column(length=20)
	public String getComplainantNo() {
		return complainantNo;
	}

	public void setComplainantNo(String complainantNo) {
		this.complainantNo = complainantNo;
	}

	@OneToOne(mappedBy = "vicId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	public Address getAddrid() {
		return addrid;
	}
	public void setAddrid(Address addrid) {
		this.addrid = addrid;
	}

	@OneToOne(mappedBy = "vId",fetch = FetchType.EAGER)
	public Photo getPhoId() {
		return phoId;
	}

	public void setPhoId(Photo phoId) {
		this.phoId = phoId;
	}
	//Convinence methods
	
	public void addAddress(Address addr)
	{
		this.setAddrid(addr);
		addr.setVicId(this);
	}
	
	public void addPhoto(Photo pho)
	{
		this.setPhoId(pho);
		pho.setvId(this);
	}

	public void removeAddress(Address addrid2)
	{
		this.setAddrid(null);
		addrid2.setVicId(null);
	}
	public void removePhoto(Photo pho)
	{
		this.setPhoId(null);
		pho.setvId(null);
	}
}
