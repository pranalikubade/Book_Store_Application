package com.example.Book_Store_Application.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "Cart_Data_Table")
@Entity
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue

    private long cartId;
    @OneToOne
    private User user;
    @ManyToOne
    private Book book;
    private long quantity;
    private long totalPrice;

    public Cart(User userData, Book bookData, long quantity) {
        this.user = userData;
        this.book = bookData;
        this.quantity = quantity;

    }

}

