package com.app.pojos;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User 
{
	private Integer uid;
	private String name;
	private String email;
	private String password;
	private UserRole role;
	private Address addId;
	private Photo usrPhoto;
	private List<Message> messages;
	public User() 
	{
		System.out.println("user ctor");
	}
	public User(String name, String email, String password, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "u_id")
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "u_name",length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "u_email",length = 30,unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password",length = 15)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "u_role",length = 10)
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
	  @OneToOne(mappedBy = "userid",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	  public Address getAddId()
	  { return addId; }
	  public void setAddId(Address addId) { this.addId = addId; }
	
	@OneToOne(mappedBy = "uId")  
	public Photo getUsrPhoto() {
		return usrPhoto;
	}
	public void setUsrPhoto(Photo usrPhoto) {
		this.usrPhoto = usrPhoto;
	}
	@OneToMany(mappedBy = "usrId",fetch = FetchType.EAGER)
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	//Convinence methods
	
	
	
			
}
