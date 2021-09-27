package com.elenine.onelibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elenine.onelibrary.model.Library;
import com.elenine.onelibrary.repository.LibraryRepository;


@Service
public class AdminService {
	
	@Autowired
	LibraryRepository repo;
	
	public List<Library> getAllLibraries() {
		return repo.findAll();
	}
	
}
