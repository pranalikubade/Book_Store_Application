package com.example.Book_Store_Application.repo;

import com.example.Book_Store_Application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query(value = "select * from book_store.order_data where order_id =:orderId", nativeQuery = true)
    Order getOrderById(long orderId);
}
