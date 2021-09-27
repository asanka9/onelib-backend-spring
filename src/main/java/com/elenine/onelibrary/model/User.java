package com.elenine.onelibrary.model;


import javax.persistence.Entity;

import javax.persistence.Id;



@Entity(name = "User")
public class User {
	
	 
	@Id
	private String id;
	private String email;
	private String name;
	private String password;
	private String role;
	private String mnumber;
	private String lid;
	private String address;
	private String nic;
	private String gender;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getMnumber() {
		return mnumber;
	}
	
	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}
	
	public String getLid() {
		return lid;
	}
	
	public void setLid(String lid) {
		this.lid = lid;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNic() {
		return nic;
	}
	
	public void setNic(String nic) {
		this.nic = nic;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

}