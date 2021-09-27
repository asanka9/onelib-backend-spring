package com.elenine.onelibrary.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elenine.onelibrary.exception.BadRequestException;
import com.elenine.onelibrary.model.Book;
import com.elenine.onelibrary.model.Library;
import com.elenine.onelibrary.model.Order;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.repository.BookRepository;
import com.elenine.onelibrary.repository.LibraryRepository;
import com.elenine.onelibrary.repository.OrderRepository;
import com.elenine.onelibrary.repository.UserRepository;
import com.elenine.onelibrary.util.DateCounter;
import com.elenine.onelibrary.util.PasswordGenarator;



@Service
public class MobileService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	LibraryRepository libraryRepo;
	
	@Autowired 
	BookRepository bookRepo;
	
	public User verifyExternalUser(String id,String password) {
		User model01 = (userRepo.findById(id).orElse(new User()));
		if (model01.getRole().equals("external")) {
			String password01 = model01.getPassword();
			password01 = PasswordGenarator.passwordGenaratorMethod(password01);
			if (password01.equals(password)) {
				return model01;
			} else {
				throw new BadRequestException("");
			}
		} else {
			throw new BadRequestException("");
		}
	}
	
	public List<Order> returnAllBorrowedBooks(String libraryId,String userNames){
		String lids = libraryId;
		String unames = userNames;
		String lidsArray [] = lids.split(" ");
		
		String unamesArray [] = unames.split(" ");
		System.out.println(unames +"  AAAAAAAAAAAA  "+unamesArray.length);
		List<Order> myList = new ArrayList<Order>();
		for (int i = 0; i < unamesArray.length; i++) {
			
			String temp = lidsArray[i] +" "+unamesArray[i];
		
			List<Order> tempLis = orderRepo.findByUserid(temp);
			for (int j = 0; j < tempLis.size(); j++) {
				myList.add(j, tempLis.get(j));
			}
			
		}
		
		List<Order> myModelList = myList;
		
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
	
	private void mapping(Order model,String date) {
		model.setCountdays(DateCounter.DateCounterMethod(date));
	}
	
	//deleteLibrary/{userID}/{deleteLid}/{lids}/{uids}
	public List<Library> deleteSelectedLibrary(String userId,String deleteId1,String lids1,String uids1){
		String lids = lids1;
		String uids = uids1;
		String deleteId = deleteId1;
		
		String tempLids [] = lids.split(" ");
		String tempUids [] = uids.split(" ");
		
		String finalTempLids [] = new String [tempLids.length-1];
		String finalTempUids [] = new String [tempUids.length-1];
		
		int j =0;
		
		for (int i = 0; i < tempUids.length; i++) {
			if (!(tempLids[i].equals(deleteId))) {
				finalTempLids[j] = tempLids[i];
				finalTempUids[j] = tempUids[i];
				j++;
			}
		}
		
		int size = (finalTempLids.length*2)-1;
		StringBuffer sbfLIDS = new StringBuffer();
		StringBuffer sbfUIDS = new StringBuffer();
		int k =0;
		for (int i = 0; i < size; i++) {
			if (i%2!=0) {
				sbfLIDS.append(" ");
				sbfUIDS.append(" ");
			} else {
				sbfLIDS.append(finalTempLids[k]);
				sbfUIDS.append(finalTempUids[k]);
				k++;
			}
		}
		
		String LIDS = sbfLIDS.toString();
		String UIDS = sbfUIDS.toString();
		
		User model= userRepo.findById(userId).orElse(null);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(LIDS);
		System.out.println(UIDS);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		model.setLid(LIDS);
		model.setNic(UIDS);
		userRepo.save(model);
		
		if (model.getLid().equals("")) {
			return null;
		} else {
			String temp [] = model.getLid().split(" ");
			List<Library> libList = new ArrayList<Library>();
			for (String string : temp) {	
			
					libList.add(libraryRepo.findById(string).orElse(null));
				
			}
			return libList;
		}
		

		
	}
	
	public User createMobileExternalUser(String userName,String email,String password) {
		User model = new User();
		model.setAddress("--");
		model.setGender("wew");
		model.setLid("");
		model.setMnumber("ew");
		model.setNic("");
		User newModel = userRepo.findById(userName).orElse(null);
		if (newModel==null) {
			model.setId(userName);
			if (password.split(" ").length>1) {
				throw new BadRequestException(" ");
			} else {
				model.setPassword(password);
				model.setEmail(email);
				model.setName(userName);
				model.setRole("external");
				userRepo.save(model);
			}
			
		} else {
			throw new  BadRequestException("");
		}
		return model;
	}
	
	
	public List<Book> returnAllBooks(String username,String bookname){
		User model = userRepo.findById(username).orElse(null);
		List<Book> books = new ArrayList<Book>();
		String lid = model.getLid();
		String lids[] = lid.split(" ");
		System.out.println("Lenght  :::: "+lids.length);
		String [] bookCodes  = new String[lids.length] ;
		for (int i = 0; i < lids.length; i++) {
			System.out.println(lids[i]+" "+bookname);
			bookCodes[i] = lids[i]+" "+bookname;
		}
		for (String string : bookCodes) {
			Book bm=	bookRepo.findById(string).orElse(null);
			if (bm != null) {
				books.add(bm);
			}
		}
		return books;
	}
	
	
	public User updateUserDetails(String name,String email,String password,String  address) {
		User model = userRepo.findById(name).orElse(null);
		if (email != null) {
			System.out.println("11111111111");
			model.setEmail(email);
		}
		if (password != null) {
			System.out.println("222222222222222");
			model.setPassword(password);
		}
		if (address != null) {
			System.out.println("3333333333333333333333333333");
			model.setAddress(address);
		}
		userRepo.save(model);
		
		return model; 
	}
	

}
