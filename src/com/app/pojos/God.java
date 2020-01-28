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
	private VerificationStatus stat;
	private String password;
	private String newPassword;
	private Integer uid;
	private String email;
	private Integer ngoId;
	private String ngoname;

	private String ngouseremail;
	private String fromuseremail;
	private String messagebody;
	private UserRole role;
	
	private String deptname;
	private String deptcity;
	private String deptstate;
	private String deptcountry;
	private String deptphoneno;
	
	private byte[] img;
	private Integer missingNumber;
	private Integer foundNumber;
	
	public Integer getMissingNumber() {
		return missingNumber;
	}
	public void setMissingNumber(Integer missingNumber) {
		this.missingNumber = missingNumber;
	}
	public Integer getFoundNumber() {
		return foundNumber;
	}
	public void setFoundNumber(Integer foundNumber) {
		this.foundNumber = foundNumber;
	}
	public God(Integer appNo, String name, Integer age, Gendor gendor, Double height, String bgrp, Date dob,
			Date missingDate, String complainantNo, String city, String state, String country, String phoneno,
			byte[] img) {
		super();
		this.appNo = appNo;
		this.name = name;
		this.age = age;
		this.gendor = gendor;
		this.height = height;
		this.bgrp = bgrp;
		this.dob = dob;
		this.missingDate = missingDate;
		this.complainantNo = complainantNo;
		this.city = city;
		this.state = state;
		this.country = country;
		this.phoneno = phoneno;
		this.img = img;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptcity() {
		return deptcity;
	}
	public void setDeptcity(String deptcity) {
		this.deptcity = deptcity;
	}
	public String getDeptstate() {
		return deptstate;
	}
	public void setDeptstate(String deptstate) {
		this.deptstate = deptstate;
	}
	public String getDeptcountry() {
		return deptcountry;
	}
	public void setDeptcountry(String deptcountry) {
		this.deptcountry = deptcountry;
	}
	public String getDeptphoneno() {
		return deptphoneno;
	}
	public void setDeptphoneno(String deptphoneno) {
		this.deptphoneno = deptphoneno;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public VerificationStatus getStat() {
		return stat;
	}
	public void setStat(VerificationStatus stat) {
		this.stat = stat;
	}
	public String getNgouseremail() {
		return ngouseremail;
	}
	public void setNgouseremail(String ngouseremail) {
		this.ngouseremail = ngouseremail;
	}
	public String getFromuseremail() {
		return fromuseremail;
	}
	public void setFromuseremail(String fromuseremail) {
		this.fromuseremail = fromuseremail;
	}
	public String getMessagebody() {
		return messagebody;
	}
	public void setMessagebody(String messagebody) {
		this.messagebody = messagebody;
	}
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getNgoId() {
		return ngoId;
	}
	public void setNgoId(Integer ngoId) {
		this.ngoId = ngoId;
	}
	public String getNgoname() {
		return ngoname;
	}
	public void setNgoname(String ngoname) {
		this.ngoname = ngoname;
	} 
	public God() 
	{
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public God(String name, String city, String state, String phoneno, String password, Integer uid, String email) {
		super();
		this.name = name;
		this.city = city;
		this.state = state;
		this.phoneno = phoneno;
		this.password = password;
		this.uid = uid;
		this.email = email;
	}
	public God(String name, String city, String state, String country, String phoneno, VerificationStatus stat,
			Integer uid, String email, String deptname, String deptcity, String deptstate, String deptcountry,
			String deptphoneno) {
		super();
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.phoneno = phoneno;
		this.stat = stat;
		this.uid = uid;
		this.email = email;
		this.deptname = deptname;
		this.deptcity = deptcity;
		this.deptstate = deptstate;
		this.deptcountry = deptcountry;
		this.deptphoneno = deptphoneno;
	}

	
}
