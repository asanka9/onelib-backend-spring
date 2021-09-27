package com.elenine.onelibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "Booktable")
public class Book {
	
	@Id
	private String id; //library id and book name
	private String bookcode; //genarate with library
	private String name;
	private String authorname;
	private String blocation;
	private double price;
	private String categoryid;
	private String libraryid;
	private String borrowed;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookcode() {
		return bookcode;
	}
	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public String getBlocation() {
		return blocation;
	}
	public void setBlocation(String blocation) {
		this.blocation = blocation;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getLibraryid() {
		return libraryid;
	}
	public void setLibraryid(String libraryid) {
		this.libraryid = libraryid;
	}
	public String getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(String borrowed) {
		this.borrowed = borrowed;
	}

	
	
	

}
