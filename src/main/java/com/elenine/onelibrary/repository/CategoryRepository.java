package com.elenine.onelibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elenine.onelibrary.model.Category;


public interface CategoryRepository extends JpaRepository<Category, String> {

	List<Category> findByLibraryid(String libraryid);
}
