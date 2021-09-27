package com.elenine.onelibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elenine.onelibrary.exception.BadRequestException;
import com.elenine.onelibrary.model.Book;
import com.elenine.onelibrary.model.Category;
import com.elenine.onelibrary.model.Library;
import com.elenine.onelibrary.model.Order;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.service.SuperUserService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/superUser")
public class SuperUserController {
	
	@Autowired
	SuperUserService service;
	
	@PostMapping("/addLibraryAndUser/{libraryName}")
	public User createSuperUserAndLibrary(@PathVariable String libraryName,@RequestBody User model ) {
		System.out.println("ADD LIBRARY AND USER");
		User newModel= service.createLibraryAndSuperUser(libraryName, model);
		if (newModel==null) {
			throw new BadRequestException("Your User name already taken");
		} else {
			return newModel;
		}
	} 
	
	@PostMapping("/createSuperUser/{libraryId}")
	public User createSuperUser (@PathVariable String libraryId,@RequestBody User model) {
		User newModel= service.createSuperUser(libraryId, model);
		if (newModel==null) {
			throw new BadRequestException("Email Address already taken");
		} else {
			return newModel;
		}
	}
	
	@PostMapping("/createUser/{libraryId}")
	public User createUser (@PathVariable String libraryId,@RequestBody User model) {
		User newModel= service.createUser(libraryId, model);
		if (newModel==null) {
			throw new BadRequestException("Email Address already taken");
		} else {
			return newModel;
		}
	}
	
	@PostMapping("/updateUsers")
	public User updateUsers(@RequestBody User model) {
		return service.updateUsers(model);
	}
	

	@GetMapping("/returnAllBooks/{libraryId}")
	public List<Book> returnAllBooks(@PathVariable String libraryId){
		return service.returnAllBooks(libraryId);
	} 
	
	@GetMapping("/returnBorrowedBooks/{libraryId}")
	public List<Order> returnBorrowedBooks(@PathVariable String libraryId){
		return service.listOfBorrowedBooks(libraryId);
	}
	
	
	@GetMapping("/searchBook/{libaryId}/{bookName}/{authorName}")
	public List<Book> searchBook(@PathVariable String libaryId,@PathVariable String bookName,@PathVariable String authorName ) {
		List<Book> newModel= service.searchBook(libaryId, bookName, authorName);
		if (newModel==null) {
			throw new BadRequestException("Email Address already taken");
		} else {
			return newModel;
		}
	}
	
	@GetMapping("/searchCategoryBook/{categoryName}/{libraryId}")
	public List<Book> returnBoookWithCategory(@PathVariable String categoryName,@PathVariable String libraryId){
		return service.returnWithCategory(libraryId, categoryName);
	}
	
	@PostMapping("/createBook/{categoryName}")
	public List<Book> createBook(@PathVariable String categoryName ,@RequestBody Book model) {
		return service.createBook(categoryName,model);
	}
	
	@PutMapping("/updateBook/{catid}")
	public List<Book> updateBook(@PathVariable String catid,@RequestBody Book lmodel) {
		//return service.updateBook(bookId, lmodel);
		return service.updateBook(catid, lmodel); 
	}
	
	@PostMapping("/deleteBook/{catId}")
	public List<Book> deleteBook(@PathVariable String catId,@RequestBody Book model) {
		System.out.println("Deleteeeeeeeeeeeeeeeeeee");
		return service.deleteBook(catId, model);
	}
	
	
	@PutMapping("/updateLibrary")
	public Library updateLibrary(@RequestBody Library model) {
		return service.updateLibrary(model);
	}
	
	@GetMapping("/getCategories/{libraryId}")
	public List<Category> returnAllCategories(@PathVariable  String libraryId){
		System.out.println("Create aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return service.returnAllCategories(libraryId);
	}
	
	@PostMapping("/createCategory")
	public List<Category> createCategory(@RequestBody Category model) {
		
		return service.createNewCategory(model);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public List<Category> updateCategory(@PathVariable String categoryID ,@RequestBody Category model) {
		return service.updateCategory(categoryID, model);
	}
	
	@GetMapping("/getLLibraryDetails/{id}")
	public Library getLibraryDetails(@PathVariable String id) {
		return service.getLibraryDetails(id);
	}
	
	@GetMapping("/userId/{id}")
	public User getUserDetails(@PathVariable String id) {
		return service.getUserDetails(id);
	}
	
	@GetMapping("/borrowBook/{lid}/{date}/{uid}/{bname}")
	public String borrowedBook(@PathVariable String lid,@PathVariable String date,@PathVariable String uid,@PathVariable String bname) {
		return service.borrowedBook(lid, date, uid, bname);
	}
	
	@DeleteMapping("/removeFromBook/{lid}/{userId}/{bookName}")
	public String removeFromBorrowedList(@PathVariable String lid,@PathVariable String userId,@PathVariable String bookName) {
		return service.deleteBookFromBorrowedBook(lid, userId, bookName);
	}
	
	@GetMapping("getGoogleLocation/{lid}")
	public String getLocation(@PathVariable String lid) {
		return service.getLocation(lid);
	}
	
	@PostMapping("updateGoogleLocation/{lid}/{location}")
	public String updateLocation(@PathVariable String lid,@PathVariable  String location) {
		return service.updateLocaton(lid, location);
	}
}