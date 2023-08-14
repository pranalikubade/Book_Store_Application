package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.BookDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.model.Book;

import java.util.List;

public interface BookService_Interface {
    ResponseDTO addBookToStore(BookDTO bookDTO);
    List<Book> getAllBooks();

    Book getBookById(long id);

    //List<Book> getBookByName(String name);

    //double getBookPriceById(long id);

    ResponseDTO updateBook(long id, BookDTO bookDTO);
    String changeBookQuantity(String token, long id, int quantity);
    String changeBookPrice(String token, long id, double price);


    String deleteBook(long id);


}
