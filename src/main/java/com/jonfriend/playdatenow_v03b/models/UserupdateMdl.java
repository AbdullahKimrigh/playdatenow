package com.jonfriend.playdatenow_v03b.models;

//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserupdateMdl {
    
	
    @NotEmpty(message="Username required.")
    @Size(min=3, max=128, message="Username must be between 3 and 30 characters.")
    private String userName;
	
    @NotEmpty(message="Email required.")
    @Email(message="Please enter a valid email.")
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private String aboutMe;
    
    private String city;
    
//    private String state;
//    
//    private String stateTerritory;
    
    private String zipCode;
    
    // begin joins     


    // end joins 
    
    // instantiate the mdl class
    public UserupdateMdl() {}

    // begin getters/setters

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

    // end getters/setters
    
}
