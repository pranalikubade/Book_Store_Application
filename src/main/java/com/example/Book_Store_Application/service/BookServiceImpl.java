package com.example.Book_Store_Application.service;
import com.example.Book_Store_Application.dto.BookDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;

import com.example.Book_Store_Application.exception.BookCustomException;
import com.example.Book_Store_Application.exception.UserCustomException;
import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.model.User;

import com.example.Book_Store_Application.repo.BookRepo;
import com.example.Book_Store_Application.utility.Jwt_Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BookServiceImpl implements BookService_Interface{
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private Jwt_Token jwt_token;
                                        //:: Adding Book To Store :://
    @Override
    public ResponseDTO addBookToStore(BookDTO bookDTO) {
        List<Book> book = bookRepo.findAll().stream().filter(data-> data.getBookName().equals(bookDTO.getBookName())).collect(Collectors.toList());
        if(book.isEmpty()){
            Book bookData = new Book(bookDTO);
            bookRepo.save(bookData);
            String token= jwt_token.createToken((int) bookData.getId());
            return new ResponseDTO("Book Added Successfully... "+token+"\n", bookData);
        }
        return new ResponseDTO("Book Already Present..", book);//increase quantity
    }
                                        //  :: Get All Book From Store ::   //
    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
                                        //  :: Get Book By ID ::  //
    @Override
    public Book getBookById(long id) {
        return bookRepo.findById(id).orElseThrow(()-> new BookCustomException(" Invalid Book ID,Please Try With Different ID"));
    }
                                    //  :: Get Book By Name ::  //
   /* @Override
    public List<Book> getBookByName(String name) {
        Book book = bookRepo.getBookByName(name);
        return bookRepo.findAll().stream().filter(data-> data.getBookName().equals(name)).collect(Collectors.toList());
    }*/
                                    // :: Getting Book Price By ID :: //
   /* @Override
    public double getBookPriceById(long id) {
        Book bookData = bookRepo.findById(id).orElseThrow(()-> new BookCustomException(" Invalid Book ID,Please Try With Different ID"));
        return bookData.getBookPrice();
    }*/
                                    // :: Update Book Details :: //
    @Override
    public ResponseDTO updateBook(long id, BookDTO bookDTO) { //call find book by id
        Book bookData = bookRepo.findById(id).orElseThrow(()-> new BookCustomException(" Invalid Book ID,Please Try With Different ID"));
        bookData.updateBook(bookDTO);
        bookRepo.save(bookData);
        return new ResponseDTO("Book Updated SuccessFully..",bookData);
    }
                                    //  :: Change Book Quantity :: //
    @Override
    public String changeBookQuantity(String token, long id, int quantity) { //call find book by id
        long idByToken = jwt_token.decodeToken(token);
        //User userData = userRepo.findById(idByToken).orElseThrow(()-> new UserCustomException(" User not found with token :: " + token));
        Book bookData = bookRepo.findById(id).orElseThrow(()-> new BookCustomException(" Invalid Book ID,Please Try With Different ID"));
        bookData.setBookQuantity(quantity);
        bookRepo.save(bookData);
        return "Successfully Changed The Quantity.."+"\n"+bookData;
    }
                                  // :: Change Book By Price :: //
    @Override
    public String changeBookPrice(String token, long id, double price) { //call find book by id
        long idByToken = jwt_token.decodeToken(token);
        //User userData = userRepo.findById(idByToken).orElseThrow(()-> new UserCustomException(" User not found with token :: " + token));
        Book bookData = bookRepo.findById(id).orElseThrow(()-> new BookCustomException(" Invalid Book ID,Please Try With Different ID"));
        bookData.setBookPrice(price);
        bookRepo.save(bookData);
        return "Successfully Changed The Price.."+"\n"+bookData;


    }
                                //:: Delete Book From Store ::  //
    @Override
    public String deleteBook(long id) { //call find book by id
        Book book = bookRepo.findById(id).orElseThrow(()-> new BookCustomException(" Invalid Book ID,Please Try With Different ID"));
        bookRepo.deleteById(id);
        return "Book Details Deleted With ID :: " + id;
    }
}
