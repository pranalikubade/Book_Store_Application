package com.example.Book_Store_Application.repo;

import com.example.Book_Store_Application.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo  extends JpaRepository<Book,Long> {
    @Query(value = "select * from book_store.user_data where name = :name",nativeQuery = true)
    Book getBookByName(String name);
}
