package com.app.pojos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class God 
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
	private String city;
	private String state;
    private String country;
	private String phoneno;
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
	public String getBgrp() {
		return bgrp;
	}
	public void setBgrp(String bgrp) {
		this.bgrp = bgrp;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getMissingDate() {
		return missingDate;
	}
	public void setMissingDate(Date missingDate) {
		this.missingDate = missingDate;
	}
	public String getComplainantNo() {
		return complainantNo;
	}
	public void setComplainantNo(String complainantNo) {
		this.complainantNo = complainantNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public God(String name, Integer age, Gendor gendor, Double height, String bgrp, Status status, Date dob,
			Date missingDate, String complainantNo, String city, String state, String country, String phoneno) {
		super();
		this.name = name;
		this.age = age;
		this.gendor = gendor;
		this.height = height;
		this.bgrp = bgrp;
		this.status = status;
		this.dob = dob;
		this.missingDate = missingDate;
		this.complainantNo = complainantNo;
		this.city = city;
		this.state = state;
		this.country = country;
		this.phoneno = phoneno;
	}
	public Integer getAppNo() {
		return appNo;
	}
	public void setAppNo(Integer appNo) {
		this.appNo = appNo;
	} 
		
	
}
