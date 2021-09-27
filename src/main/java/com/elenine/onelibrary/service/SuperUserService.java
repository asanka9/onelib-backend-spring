package com.elenine.onelibrary.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elenine.onelibrary.exception.BadRequestException;
import com.elenine.onelibrary.model.Book;
import com.elenine.onelibrary.model.Category;
import com.elenine.onelibrary.model.Library;
import com.elenine.onelibrary.model.Order;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.repository.BookRepository;
import com.elenine.onelibrary.repository.CategoryRepository;
import com.elenine.onelibrary.repository.LibraryRepository;
import com.elenine.onelibrary.repository.OrderRepository;
import com.elenine.onelibrary.repository.UserRepository;
import com.elenine.onelibrary.util.DateCounter;
import com.elenine.onelibrary.util.GenarateLibraryCode;
import com.elenine.onelibrary.util.RandomPassword;


@Service
public class SuperUserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	LibraryRepository libraryRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	
	public User createLibraryAndSuperUser (String libraryname,User  model){
		
		User uModel = model; 
		System.out.println(uModel);
		uModel.setEmail(uModel.getId());
		uModel.setId(GenarateLibraryCode.libraryCodeGenaratorMethod(libraryname, libraryRepo.findAll().size()) +" "+uModel.getName());
		uModel.setRole("SUPERUSER");
		uModel.setPassword(RandomPassword.RandomPasswordMethod());
		
		Library lModel = new Library() ;
		lModel.setName(libraryname);
		lModel.setAddress(uModel.getAddress());
		lModel.setEmail("--");
		lModel.setTel("--");
		
		lModel.setId(GenarateLibraryCode.libraryCodeGenaratorMethod(libraryname, libraryRepo.findAll().size()));
		uModel.setLid(GenarateLibraryCode.libraryCodeGenaratorMethod(libraryname, libraryRepo.findAll().size()));
		userRepo.save(uModel);
		libraryRepo.save(lModel);
		return model;
	}
	
	public User createSuperUser(String libraryId,User uModel) {
		User fModel = userRepo.findById(
				libraryId+" "+uModel.getName()
				).orElse(null);
		if (fModel==null) {
			uModel.setId(libraryId+" "+uModel.getName());
			uModel.setRole("SUPERUSER");
			uModel.setLid(libraryId);
			uModel.setPassword(RandomPassword.RandomPasswordMethod());
			userRepo.save(uModel);
			return uModel;
		} else { 
			return null;
		}

	}
	
	public User createUser(String libraryId,User uModel) {
		User fModel = userRepo.findById(
				libraryId+" "+uModel.getName()
				
				).orElse(null);
		if (fModel==null) {
			uModel.setId(libraryId+" "+uModel.getName());
			uModel.setRole("USER");
			uModel.setLid(libraryId);
			uModel.setPassword(RandomPassword.RandomPasswordMethod());
			userRepo.save(uModel);
			return uModel;
		} else {
			return null;
		}
	}
	
	public User updateUsers(User newModel) {
		User oldModel = userRepo.findById(newModel.getId()).orElse(null);
		if ((newModel.getName()).equals(oldModel.getName())) {
			userRepo.deleteById(newModel.getId());
			userRepo.save(newModel);
			return newModel;
		} else {
			userRepo.deleteById(newModel.getId());
			if (newModel.getRole().equals("external")) {
				newModel.setId(newModel.getName()); 
			} else {
				newModel.setId(newModel.getLid()+" "+newModel.getName());
			}
			
			User temp = userRepo.findById(newModel.getId()).orElse(null);
			if (temp!=null) {
				throw new BadRequestException("Already have");
			}
			userRepo.save(newModel);
			return newModel;
		}
	}
	
	
	
	public List<User> deleteUsers(String userId) {
		userRepo.deleteById(userId);
		return userRepo.findAll();
	}
	
	public List<Book> searchBook(String libraryId,String bookName,String bookAuthor){
		
		System.out.println(bookName);
		System.out.println(bookAuthor);
		
		
		if (bookAuthor.equals("undefined")) {
			return bookRepo.findByBookByBookName(libraryId, bookName);
		} else if(bookName.equals("undefined")) {
			return bookRepo.findWithBookAuthor(libraryId, bookAuthor);
		}else {
			return bookRepo.findWithBookNameBookAuthor(libraryId, bookName, bookAuthor);
		}
		
	}
	
	public List<Book> returnAllBooks(String libraryId){
		return bookRepo.findByLibraryid(libraryId);
	}

	public List<Book> returnWithCategory(String libraryId,String categoryName){
		return bookRepo.findWithBookCategory(libraryId, categoryName);
	}
	
	public List<Book> createBook(String categoryId,Book model){
		model.setId(model.getLibraryid()+" "+model.getName());
		Book mo=bookRepo.findById(model.getId()).orElse(null);
		if (mo!=null) {
			throw  new BadRequestException("Book Name Already Taken");
		} 
		model.setCategoryid(categoryId);
		model.setBorrowed("YES");
		Category catModel = categoryRepo.findById(model.getLibraryid()+" "+categoryId).orElse(null);
		categoryRepo.deleteById(model.getLibraryid()+" "+categoryId);
		catModel.setNumofbooks(catModel.getNumofbooks()+1);
		categoryRepo.save(catModel);
		bookRepo.save(model);
		return  bookRepo.findWithBookCategory(model.getLibraryid(), categoryId);
	}
	
	public List<Book> updateBook(String categoryName,Book model){
		Book old = bookRepo.findById(model.getId()).orElse(new Book());
		if ((model.getName().equals(old.getName()))) {
			old = model;
			bookRepo.deleteById(model.getId());
			bookRepo.save(old);
			return bookRepo.findWithBookCategory(model.getLibraryid(), categoryName);
		}
		else {
			bookRepo.deleteById(model.getId());
			model.setId(model.getLibraryid()+" "+model.getName());
			if (bookRepo.findById(model.getId()).orElse(null)==null) {
				bookRepo.save(model);
				return bookRepo.findWithBookCategory(model.getLibraryid(), categoryName);
			} else {
				throw new BadRequestException("Book Name Already Taken");
			}


		}

	}
	
	public List<Book> deleteBook(String categoryName,Book  model){
		
		bookRepo.deleteById(model.getId());
		Category catModel = categoryRepo.findById(model.getLibraryid()+" "+categoryName).orElse(null);
		categoryRepo.deleteById(model.getLibraryid()+" "+categoryName);
		catModel.setNumofbooks(catModel.getNumofbooks()-1);
		categoryRepo.save(catModel);
		return bookRepo.findWithBookCategory(model.getLibraryid(), categoryName);

	}
	
	public List<Category> createCategory(Category model){
		model.setId(model.getLibraryid()+" "+model.getCategoryname());
		categoryRepo.save(model);
		return categoryRepo.findAll();

	}
	
	public List<Category> updateCategory(String categoryId,Category model){
		Category old=categoryRepo.findById(categoryId).orElse(new Category());
		model.setId(model.getLibraryid()+" "+model.getCategoryname());
		old=model;
		return categoryRepo.findAll();
		

	}
	
	public Library updateLibrary(Library model) {
		libraryRepo.deleteById(model.getId());
		libraryRepo.save(model);
		return model;

	}
	
	public List<Category> returnAllCategories(String libraryId){
		return categoryRepo.findByLibraryid(libraryId);
	}
	
	public List<Order> returnBorrowedBooks(String l){
		return orderRepo.findByLibraryid(l);
	}
	
	

	
	public List<Order> listOfBorrowedBooks(String  id){
		List<Order> myModelList = orderRepo.findByLibraryid(id);
		myModelList.stream().forEach(x->mapping(x, x.getDate02()));
		Comparator<Order> com = new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				if (o1.getCountdays()<o2.getCountdays()) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		Collections.sort(myModelList, com);
		
		return myModelList;
	}
	
	public Library getLibraryDetails(String id) {
		return libraryRepo.findById(id).orElse(null);
	}
	
	public User getUserDetails(String id) {
		User model= userRepo.findById(id).orElse(null);
		if (model==null) {
			throw new BadRequestException("Can not Find User");
		} else {
			return model;
		}
	}
	
	public String borrowedBook(String lid,String date,String uid,String bname) {
		Order model = new Order();
		User uModel = userRepo.findById(uid).orElse(null);
		if (uModel==null) {
			throw new BadRequestException("No User");
		}
		String authorName = (bookRepo.findById(lid+" "+bname).orElse(null)).getAuthorname();
		if (authorName==null) {
			throw new BadRequestException("No User");
		}
		model.setAuthorname(authorName);
		model.setId(lid+" "+uid+" "+bname);
		model.setDate02(date);
		ZoneId zone = ZoneId.of("Asia/Colombo");
		LocalDate today = LocalDate.now(zone);
		String currentDate = today.toString();
		model.setDate01(currentDate);
		model.setBookname(bname);
		model.setLibraryid(lid);
		model.setUserid(uid);
		orderRepo.save(model);
		return "";
	}
	
	public String deleteBookFromBorrowedBook(String libraryId,String uId,String bookName) {
		orderRepo.deleteById(libraryId+" "+uId+" "+bookName);
		return "Successs";
	}
	
	public List<Category> createNewCategory (Category model){
		model.setId(model.getLibraryid()+" "+model.getCategoryname());
		model.setNumofbooks(0);
		Category model2 = categoryRepo.findById(model.getId()).orElse(null);
		if (model2!=null) {
			throw new BadRequestException("Already have category name");
		}
		categoryRepo.save(model);
		return categoryRepo.findByLibraryid(model.getLibraryid());
	}
	
	
	
	private void mapping(Order model,String date) {
		model.setCountdays(DateCounter.DateCounterMethod(date));
	}
	
	
	public String getLocation(String lid) {
		Library model = libraryRepo.findById(lid).orElse(null);
		return model.getGooglelocation();
	}
	
	public String updateLocaton(String lid,String googlelocation) {
		Library model = libraryRepo.findById(lid).orElse(null);
		libraryRepo.delete(model);
		model.setGooglelocation(googlelocation);
		libraryRepo.save(model);
		return null;
	}
	


}
