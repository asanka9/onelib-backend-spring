package com.elenine.onelibrary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elenine.onelibrary.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByName(String name);
	Optional<User> findById(String id);
	
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
	
}

