package com.elenine.onelibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elenine.onelibrary.model.Library;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.service.AdminService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;
	
	@GetMapping("/getLibrary")
	public List<Library> getLibrary(){
		return service.getAllLibraries();
	}
	
	@PostMapping("/createAdmin")
	public User createAdmin() {
		return null;
	}
		
	
}

