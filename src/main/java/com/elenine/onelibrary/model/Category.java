package com.elenine.onelibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "Categorytable")
public class Category {
	
	@Id
	private String id; //This id genarate with library is
	private  String categoryname;
	private String libraryid;
	private int numofbooks;
	private String  description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getLibraryid() {
		return libraryid;
	}
	public void setLibraryid(String libraryid) {
		this.libraryid = libraryid;
	}
	public int getNumofbooks() {
		return numofbooks;
	}
	public void setNumofbooks(int numofbooks) {
		this.numofbooks = numofbooks;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}