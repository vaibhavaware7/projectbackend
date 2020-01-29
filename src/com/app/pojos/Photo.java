package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo 
{
	private Integer pId;
	private byte[] img;
	private User uId;
	private Victim vId;
	private PhotoOwner owner;
	public Photo() {
		System.out.println("in photo ctor");
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	@Lob
	@Column(name = "img")
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	@OneToOne
	@JoinColumn(name = "u_id")
	public User getuId() {
		return uId;
	}
	public void setuId(User uId) {
		this.uId = uId;
	}

	@OneToOne
	@JoinColumn(name = "v_id")
	public Victim getvId() {
		return vId;
	}
	public void setvId(Victim vId) {
		this.vId = vId;
	}
	
	@Enumerated(EnumType.STRING)
	public PhotoOwner getOwner() {
		return owner;
	}
	public void setOwner(PhotoOwner owner) {
		this.owner = owner;
	}
	
}
