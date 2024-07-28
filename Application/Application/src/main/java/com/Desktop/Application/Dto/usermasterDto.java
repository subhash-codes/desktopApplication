package com.Desktop.Application.Dto;



import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class usermasterDto {

	    private String username;
	    private String password;

	    // Getters and setters
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}
