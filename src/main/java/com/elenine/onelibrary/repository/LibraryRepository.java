package com.elenine.onelibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elenine.onelibrary.model.Library;


@Repository
public interface LibraryRepository extends JpaRepository<Library, String> {

}
