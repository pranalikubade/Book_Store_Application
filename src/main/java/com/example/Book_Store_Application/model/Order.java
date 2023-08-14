package com.example.Book_Store_Application.model;

import com.example.Book_Store_Application.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Order_Data_Table")
public class Order {
    @Id
    @GeneratedValue
    private long orderId;
    private LocalDate orderDate;
    private double totalPrice;
    private long quantity;
    private String address;


    @OneToOne
    private User userId;


    @ManyToOne
    private Book bookId;
    private boolean cancel;


    public Order(OrderDTO orderDTO, User userId, Book bookId, double totalPrice, long quantity) {
        this.quantity=quantity;
        this.bookId = bookId;
        this.userId = userId;
        this.orderDate = LocalDate.now();
        this.address=orderDTO.getAddress();
        this.totalPrice = totalPrice;
    }

}
