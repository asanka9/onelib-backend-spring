package com.elenine.onelibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elenine.onelibrary.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND name =?2 AND authorname =?3")
	List<Book> findWithBookNameBookAuthor(String library_id,String book_name,String book_author);
	
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND name =?2")
	List<Book> findByBookByBookName(String library_id,String book_name);
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND authorname =?2")
	List<Book> findWithBookAuthor(String library_id,String book_author);
	
	List<Book> findByLibraryid(String libraryid);
	
	@Query("FROM Booktable  WHERE libraryid =?1 AND categoryid =?2")
	List<Book> findWithBookCategory(String library_id,String categoryid);


}
