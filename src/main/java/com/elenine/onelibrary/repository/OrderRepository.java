package com.elenine.onelibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elenine.onelibrary.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	
	
	List<Order> findByUserid(String id);
	
	List<Order> findByLibraryid(String libraryid);
	
	@Query("FROM Ordertable  WHERE userid =?1")
	Order findByexUserid(String id);
}
