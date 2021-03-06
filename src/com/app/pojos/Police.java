package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="police")
public class Police
{
	private Integer deptId;
	private String name;
	private Address poaddrId;
	private User pouserid;
	public Police() {
		System.out.println("police ctor");
	}
	public Police(String name) {
		super();
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	public Integer getDeptId() {
		return deptId;
	}
	
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "dept_name",length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToOne(mappedBy = "polId")
	public Address getPoaddrId() {
		return poaddrId;
	}
	public void setPoaddrId(Address poaddrId) {
		this.poaddrId = poaddrId;
	}
	@OneToOne
	@JoinColumn(name="userid")
	public User getPouserid() {
		return pouserid;
	}
	public void setPouserid(User pouserid) {
		this.pouserid = pouserid;
	}
	public void addAddress(Address addr)
	{
		this.setPoaddrId(addr);
		addr.setPolId(this);
	}
	public void removeAddress(Address add)
	{
		this.setPoaddrId(null);
		add.setPolId(null);
	}
}
