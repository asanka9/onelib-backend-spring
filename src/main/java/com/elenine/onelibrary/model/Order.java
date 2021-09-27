package com.elenine.onelibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="Ordertable")
public class Order {
	
	@Id
	private String id; //this is same with book id
	private String bookname;
	private String authorname;
	private String libraryid;
	private String userid;
	private String date01;
	private String date02;
	private int countdays;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getLibraryid() {
		return libraryid;
	}
	public void setLibraryid(String libraryid) {
		this.libraryid = libraryid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDate01() {
		return date01;
	}
	public void setDate01(String date01) {
		this.date01 = date01;
	}
	public String getDate02() {
		return date02;
	}
	public void setDate02(String date02) {
		this.date02 = date02;
	}
	public int getCountdays() {
		return countdays;
	}
	public void setCountdays(int countdays) {
		this.countdays = countdays;
	}

	

}