package com.app.pojos;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User 
{
	private Integer uid;
	private String name;
	private String email;
	private String password;
	private UserRole role;
	private VerificationStatus stat;
	@JsonIgnore
	private Address addId;
	@JsonIgnore
	private Ngo ngoid;
	@JsonIgnore
	private Police poId;
	@JsonIgnore
	private Photo usrPhoto;
	@JsonIgnore
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
	@Enumerated(EnumType.STRING)
	@Column(name = "v_status",length = 10)
	public VerificationStatus getStat() {
		return stat;
	}
	public void setStat(VerificationStatus stat) {
		this.stat = stat;
	}
	@OneToOne(mappedBy = "userid")
	public Ngo getNgoid() {
		return ngoid;
	}
	public void setNgoid(Ngo ngoid) {
		this.ngoid = ngoid;
	}
	@OneToOne(mappedBy = "pouserid")
	public Police getPoId() {
		return poId;
	}
	public void setPoId(Police poId) {
		this.poId = poId;
	}
	
	//Convinence methods
	public void addMessage(Message m)
	{
		this.messages.add(m);
		m.setUsrId(this);
	}
	public void addAddress(Address a)
	{
		this.setAddId(a);
		a.setUserid(this);
	}
	public void removeAddress(Address a)
	{
		this.setAddId(null);
		a.setUserid(null);
	}
	public void addPoliceStation(Police pol)
	{
		this.setPoId(pol);
		pol.setPouserid(this);
		
	}
	public void removePolice(Police po)
	{
		this.setPoId(null);
		po.setPouserid(null);
	}
	public void addNgoOfUser(Ngo ngo) {
		this.setNgoid(ngo);
		ngo.setUserid(this);
	}
	public void removeNgo(Ngo ngo)
	{
		this.setNgoid(null);
		ngo.setUserid(null);
	}
	public void addPhoto(Photo pho)
	{
		this.setUsrPhoto(pho);
		pho.setuId(this);
	}
			
}
