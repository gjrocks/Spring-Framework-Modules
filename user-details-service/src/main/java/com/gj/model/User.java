package com.gj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String fname;
	private String lname;
	private String address;
	private String userid;
	private String mobileNumber;
	private String email;


	public User() {
		super();
	}
	
	
	
	public User(String fname, String lname, String address, String userid, String mobileNumber, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.userid = userid;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}



	public User(long id, String fname, String lname, String address, String userid, String mobileNumber, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.userid = userid;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	

}
