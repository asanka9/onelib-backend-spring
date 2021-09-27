package com.elenine.onelibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "Librarytable")
public class Library {

	@Id
	private String id;
	private String name;
	private String address;
	private String googlelocation;
	private String email;
	private String tel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGooglelocation() {
		return googlelocation;
	}
	public void setGooglelocation(String googlelocation) {
		this.googlelocation = googlelocation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	
}
