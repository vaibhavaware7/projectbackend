
  package com.app.pojos;
  
  import javax.persistence.*;
  
  @Entity
  @Table(name = "address") 
  public class Address 
  {
	  private Integer add_id;
      private String city;
      private String state;
      private String country;
  private String phoneno; 
  private User userid; 
  private Victim vicId;
  private Ngo ngoKey;
  private Police polId;
  public Address() 
  {
  System.out.println("Address ctor"); } 
  public Address(String city, String
  state, String country, String phoneno) { super(); this.city = city;
  this.state = state; this.country = country; this.phoneno = phoneno; }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Integer getAdd_id() {
	return add_id;
}
public void setAdd_id(Integer add_id) {
	this.add_id = add_id;
}
@Column(length = 20) public String getCity() { return city; } public void
  setCity(String city) { this.city = city; }
  
  @Column(length = 20) public String getState() { return state; } public void
  setState(String state) { this.state = state; }
  
  @Column(length = 20) public String getCountry() { return country; } public
  void setCountry(String country) { this.country = country; }
  
  @Column(length = 20) public String getPhoneno() { return phoneno; } public
  void setPhoneno(String phoneno) { this.phoneno = phoneno; }
  
	
	  @OneToOne
	  @JoinColumn(name = "user_id")
	  public User getUserid()
	  { return userid; }
	  public void setUserid(User userid) { this.userid = userid; }
	
	@OneToOne
	@JoinColumn(name = "vic_id")
	public Victim getVicId() {
		return vicId;
	}
	public void setVicId(Victim vicId) {
		this.vicId = vicId;
	}
	
	@OneToOne
	@JoinColumn(name = "ngo_id")
	public Ngo getNgoKey() {
		return ngoKey;
	}
	public void setNgoKey(Ngo ngoKey) {
		this.ngoKey = ngoKey;
	}
	@OneToOne
	@JoinColumn(name = "police_id")
	public Police getPolId() {
		return polId;
	}
	public void setPolId(Police polId) {
		this.polId = polId;
	}
	  
	  
 }
 