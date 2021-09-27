package com.elenine.onelibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "Webdatatable")
public class WebData {
	
	@Id
	private int id;
	private String categoryimagelist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryimagelist() {
		return categoryimagelist;
	}
	public void setCategoryimagelist(String categoryimagelist) {
		this.categoryimagelist = categoryimagelist;
	}
	
	
	
}
