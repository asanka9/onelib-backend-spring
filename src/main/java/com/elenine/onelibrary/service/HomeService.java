package com.elenine.onelibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elenine.onelibrary.exception.BadRequestException;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.repository.LibraryRepository;
import com.elenine.onelibrary.repository.UserRepository;
import com.elenine.onelibrary.util.PasswordGenarator;


@Service
public class HomeService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	LibraryRepository lrepo;
	
	
	public int numOfusers() {
		return repo.findAll().size();
	}
	
	public int numOfLibraries() {
		return lrepo.findAll().size();
	}
	
	public User loginVerification(String email,String pass) {
		System.out.println("++++++++++++++++++++++");
		System.out.println(email);
		System.out.println(pass);
		System.out.println("++++++++++++++++++++++");
		User model01 = (repo.findById(email).orElse(new User()));
		String password01 = model01.getPassword();
		password01 = PasswordGenarator.passwordGenaratorMethod(password01);
		if (password01.equals(pass)) {
			return model01;
		} else {
			return null;
		}
	}
	
	public User createExternalUser(User model) {
		model.setRole("external");
		User uModel=repo.findById(model.getId()).orElse(null);
		if (uModel!=null) {
			throw new BadRequestException("already have account");
		}else {
			// String encodedPassword = passwordEncoder.encode(user.getPassword());
			model.setPassword((new BCryptPasswordEncoder()).encode(model.getPassword()));
			model.setName(model.getId());
			repo.save(model);
			return model;
		}
		
	}
}
