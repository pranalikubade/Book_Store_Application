package com.example.Book_Store_Application.controller;

import com.example.Book_Store_Application.dto.BookDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.service.BookService_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService_Interface iBookServices;
    @PostMapping("addBook")
    public ResponseEntity<ResponseDTO> addBookToStore(@RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = iBookServices.addBookToStore(bookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("allBooks")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        List<Book> books = iBookServices.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("All book records retrieved successfully !", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("getBookById")
    public ResponseEntity<ResponseDTO> getBookById(@RequestHeader long id) {
        Book book = iBookServices.getBookById(id);
        ResponseDTO responseDTO = new ResponseDTO("Book Data With ID :: " + id, book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /* @GetMapping(value = "getBookByName")
    public List<Book> getBookByName(@RequestParam String name) {
        return iBookServices.getBookByName(name);
    }*/

    @PutMapping("updateBook")
    public ResponseEntity<ResponseDTO> updateBook(@RequestHeader long id, @RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = iBookServices.updateBook(id, bookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteBook")
    public ResponseEntity<ResponseDTO> deleteBook(@RequestHeader long id) {
        String book = iBookServices.deleteBook(id);
        ResponseDTO responseDTO = new ResponseDTO("Book Data With ID  Deleted Successfully:: " + id, book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
       @PostMapping("changePriceBook/{id}")//request header
       public ResponseEntity<ResponseDTO> changePrice(@RequestHeader("token") String token,@PathVariable long id,@RequestParam double price) {
           String book = iBookServices.changeBookPrice(token, id, price);
           ResponseDTO responseDTO = new ResponseDTO("Book Data With ID Price changed Successfully:: " + id, book);
           return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @PostMapping("changeQuantityBook/{id}")//request header
    /*public String changeQuantity(@PathVariable("token") String token, @RequestHeader long id, @RequestHeader int quantity) {
        return iBookServices.changeBookQuantity(token, id, quantity);*/

    public ResponseEntity<ResponseDTO> changeQuantity(@RequestHeader("token") String token,@PathVariable long id,@RequestParam int quantity) {
        String book = iBookServices.changeBookQuantity(token, id, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Book Data With ID Quantity changed Successfully:: " + id, book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}

