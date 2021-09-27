package com.elenine.onelibrary.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elenine.onelibrary.exception.BadRequestException;
import com.elenine.onelibrary.model.Library;
import com.elenine.onelibrary.model.Order;
import com.elenine.onelibrary.model.User;
import com.elenine.onelibrary.repository.LibraryRepository;
import com.elenine.onelibrary.repository.OrderRepository;
import com.elenine.onelibrary.repository.UserRepository;
import com.elenine.onelibrary.util.DateCounter;


@Service
public class UserControllerService {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	OrderRepository  orderRepo;
	
	@Autowired
	LibraryRepository libRepo;
	
	
	public String getLibraryname(String libraryId) {
		Library model= libRepo.findById(libraryId).orElse(null);
		return model.getName();
	}
	
	public User updateUserDetails(String id,User newModel) {
		User model=repo.findById(id).orElse(new User());
		model.setAddress(newModel.getAddress());
		model.setName(newModel.getName());
		model.setNic(newModel.getNic());
		model.setPassword(newModel.getPassword());
		model.setId(newModel.getId());
		model.setMnumber(newModel.getMnumber());
		return model;
	}
	/*
	public List<OrderModel> listOfBorrowedBooks(String  id){
		List<OrderModel> myModelList = orderRepo.findByUserid(id);
		myModelList.stream().forEach(x->mapping(x, x.getDate02()));
		Comparator<OrderModel> com = new Comparator<OrderModel>() {
			@Override
			public int compare(OrderModel o1, OrderModel o2) {
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
	
	*/
	
	private void mapping(Order model,String date) {
		model.setCountdays(DateCounter.DateCounterMethod(date));
	}
	
	public List<Library> addLibraryToExternalUserAccount(String userId,String userPassword,User model){
		
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHKKKKKKKKKKKKKKKKKKKKKKKK");
		System.out.println("KKKKKKKKKKKKJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
		
		if (model.getLid()==null || model.getLid().equals("")) {
			System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			User uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(temp[0]);
					model.setNic(temp[1]); 
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			}
		}else {
			System.out.println("sasasasas");
			System.out.println("||||||||||||| --------"+model.getLid());
			User uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(model.getLid()+" "+temp[0]);
					model.setNic(model.getNic()+" "+temp[1]);
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			}
		}
		
	}
	
	
	public List<Library> getListOfLibraries(String lids){
		String temp [] = lids.split(" ");

		List<Library> libList = new ArrayList<Library>();
		for (String string : temp) {	
			
				System.out.println(temp);
				libList.add(libRepo.findById(string).orElse(null));
			
		}
		System.out.println("####  "+libList);
		System.out.println("Size  "+libList.size());
		return libList;
		
	}
	
	public List<Order> returnAllBooks(User model){
		String lids = model.getLid();
		String unames = model.getNic();
		String lidsArray [] = lids.split(" ");
		String unamesArray [] = unames.split(" ");
		List<Order> myList = new ArrayList<Order>();
		for (int i = 0; i < unamesArray.length; i++) {
			
			String temp = lidsArray[i] +" "+unamesArray[i];
			System.out.println("===============================================  "+temp);
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
	
	public User returnuserDetails(String userDetails) {
		return repo.findById(userDetails).orElse(null);
	}
	

	
	public List<Library> addLibraryToExternalUserAccount01(String userId,String userPassword,User model){
		
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHKKKKKKKKKKKKKKKKKKKKKKKK");
		System.out.println("KKKKKKKKKKKKJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
		
		if (model.getLid().equals(userId)) {
			System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
			User uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(temp[0]);
					model.setNic(temp[1]); 
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			} 
		}else {
			System.out.println("||||||||||||| --------"+userId);
			System.out.println("||||||||||||| --------"+model.getLid());
			User uModel = repo.findById(userId).orElse(null);
			if (uModel==null) {
				throw new BadRequestException("");
			} else {
				if (((uModel.getPassword()).equals(userPassword))&&((uModel.getRole()).equals("USER"))) {
					String temp [] = userId.split(" ");
					model.setLid(model.getLid()+" "+temp[0]);
					model.setNic(model.getNic()+" "+temp[1]);
					repo.deleteById(model.getId());
					repo.save(model);
					return getListOfLibraries(model.getLid());
				} else {
					throw new BadRequestException("");
				}
			}
		}
		
	}
	
	/*
	 * 
	 * I/flutter ( 4599): http://10.0.2.2:8080/mobile/
	 * addLibrary/RJP4 user4/asanka96/abc/93-hakuruwela/Asanka Gayshan/aaa/external/0703962577/RJP4 user4/hakuruwela/asanka96/male

	 * 
	 * */
	
}
