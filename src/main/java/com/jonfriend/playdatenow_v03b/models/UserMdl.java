package com.jonfriend.playdatenow_v03b.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user")
public class UserMdl {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	// begin: entity-specific fields
	
    @NotEmpty(message="Username required.")
    @Size(min=8, max=128, message="Username must be between 3 and 30 characters.")
    private String userName;
    
    @NotEmpty(message="Email required.")
    @Email(message="Please enter a valid email.")
    private String email;
    
    @NotEmpty(message="Password required.")
    @Size(min=8, max=128, message="Password must be between 8 and 20 characters.")
    private String password;
    
    @Transient
//    @NotEmpty(message="Confirm Password is required!")  // this is not necessary
//    @Size(min=3, max=128, message="Confirm Password must match password") // this is not necessary
    private String confirm;
    
    private String firstName;
    
    private String lastName;
    
    private String aboutMe;
    
    private String city;
    
    private String zipCode;
    
    // end entity-specific fields
    
    // begin joins
    
    // join playdate
    @OneToMany(mappedBy="userMdl", fetch = FetchType.LAZY)
    private List<PlaydateMdl> playdateList; 

    // join rsvp
    @OneToMany(mappedBy="userMdl", fetch = FetchType.LAZY)
    private List<RsvpMdl> rsvpList; 
    
	
    // end joins 
    
    // instantiate the mdl
    public UserMdl() {}

    // add methods to populate maintain createdAt/UpdatedAt
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    // begin: getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<PlaydateMdl> getPlaydateList() {
		return playdateList;
	}

	public void setPlaydateList(List<PlaydateMdl> playdateList) {
		this.playdateList = playdateList;
	}

	public List<RsvpMdl> getRsvpList() {
		return rsvpList;
	}

	public void setRsvpList(List<RsvpMdl> rsvpList) {
		this.rsvpList = rsvpList;
	}

    // end G/S
  
}

