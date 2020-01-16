package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message 
{
	private Integer mId;
	private String msg;
	private User usrId;
	public Message() {
		System.out.println("message ctor");
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getmId() {
		return mId;
	}
	
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	@Lob
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@ManyToOne
	@JoinColumn(name = "u_id")
	public User getUsrId() {
		return usrId;
	}
	public void setUsrId(User usrId) {
		this.usrId = usrId;
	}
	
}
