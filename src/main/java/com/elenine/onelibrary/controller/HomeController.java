package com.elenine.onelibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elenine.onelibrary.exception.NotFoundingException;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.service.HomeService;


@CrossOrigin("*")
@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	HomeService service;
	
	@GetMapping("/numOfUsers")
	public int numOfUsers() {
		return service.numOfusers();
	}
	
	@GetMapping("/numOfLibraries")
	public int numOflibraries() {
		return service.numOfLibraries();
	}
	
	@GetMapping("/login/{email}/{password}")
	public User loginUser(@PathVariable String email,@PathVariable String password) {
		User model= service.loginVerification(email,password);

		if (model==null) {
			throw new NotFoundingException("No User Finding !!");
		} else {
			return model;
		}
	}
	
	@PostMapping("/externalUserCreate")
	public User createExternalUser(@RequestBody  User model) {
		System.out.println("CALLING");
		return service.createExternalUser(model);
	}
}