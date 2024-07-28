package com.Desktop.Application.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class usermaster {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
	private int userid;
	private String username;
	private String password;
	private LocalDate createdon;
	public usermaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public usermaster(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public usermaster(String username, String password, LocalDate createdon) {
		super();
		this.username = username;
		this.password = password;
		this.createdon = createdon;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
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
	public LocalDate getCreatedon() {
		return createdon;
	}
	public void setCreatedon(LocalDate createdon) {
		this.createdon = createdon;
	}
	@Override
	public String toString() {
		return "usermaster [userid=" + userid + ", username=" + username + ", password=" + password + ", createdon="
				+ createdon + "]";
	}

}
