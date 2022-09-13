package com.jonfriend.playdatenow_v03b.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUserMdl {
    
    @NotEmpty(message="Email is required.")
    @Email(message="Please enter a valid email!")
    private String email;
    
    // jrf: think about how to maek this mdl: login with email OR userName
    
    @NotEmpty(message="Password is required.")
    // update size constraint to be greater than 1 (8?) when ready to move beyond testing.
    @Size(min=1, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    public LoginUserMdl() {}

    // begin GS
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

    // end GS
    
    
    
}
