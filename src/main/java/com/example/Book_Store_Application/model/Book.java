package com.example.Book_Store_Application.model;

import com.example.Book_Store_Application.dto.BookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Data
@Table(name = "Book_Data_Table")
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue

    private long Id;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;//image
    private double bookPrice;
    private int bookQuantity;


public void updateBook(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.bookAuthor = bookDTO.getBookAuthor();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookPrice = bookDTO.getBookPrice();
        this.bookQuantity = bookDTO.getBookQuantity();
        this.bookLogo = bookDTO.getBookLogo();
    }

 public Book(BookDTO bookDTO) {
        this.updateBook(bookDTO);
    }



}

