package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="ngos")
public class Ngo
{
	private Integer ngoId;
	private String ngoname;
	private Address ngoAddrId;
	private User userid;
	public Ngo() {
		System.out.println("in NGO ctor");
	}
	public Ngo(String ngoname) {
		super();
		this.ngoname = ngoname;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getNgoId() {
		return ngoId;
	}
	public void setNgoId(Integer ngoId) {
		this.ngoId = ngoId;
	}
	@Column(name = "name",length = 30)
	public String getNgoname() {
		return ngoname;
	}
	public void setNgoname(String ngoname) {
		this.ngoname = ngoname;
	}
	@OneToOne(mappedBy = "ngoKey")
	public Address getNgoAddrId() {
		return ngoAddrId;
	}
	public void setNgoAddrId(Address ngoAddrId) {
		this.ngoAddrId = ngoAddrId;
	}
	@OneToOne
	@JoinColumn(name = "user_id")
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	public void addAddress(Address a)
	{
		this.setNgoAddrId(a);
		a.setNgoKey(this);
	}
	
}
