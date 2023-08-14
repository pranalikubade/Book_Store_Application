package com.example.Book_Store_Application.dto;

import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.model.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Data;

@Data
public class CartDTO {
    public long userId;
    public long bookId;
    public long quantity;

}
