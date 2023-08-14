package com.example.Book_Store_Application.repo;

import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query(value = "select * from book_store.cart_data where user_data_user_id = :userId",nativeQuery = true)
    List<Cart> findByUserId(long userId);

}
