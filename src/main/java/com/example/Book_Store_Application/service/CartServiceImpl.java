package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.CartDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.exception.BookCustomException;
import com.example.Book_Store_Application.exception.UserCustomException;
import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.model.Cart;
import com.example.Book_Store_Application.model.User;
import com.example.Book_Store_Application.repo.BookRepo;
import com.example.Book_Store_Application.repo.CartRepo;
import com.example.Book_Store_Application.repo.UserRepo;
import com.example.Book_Store_Application.utility.Jwt_Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService_Interface{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private Jwt_Token jwt_token;


    /*public ResponseDTO addCart(CartDTO cartDTO) {
       Cart cartItem =  new Cart(userRepo.findById(cartDTO.getUserId()).orElseThrow(()-> new UserCustomException(" User not found with UserID :: " + cartDTO.getUserId())), bookRepo.findById(cartDTO.getBookId()).orElseThrow(()-> new BookCustomException("User not Found with BookID :: " + cartDTO.getBookId())), cartDTO.getQuantity());
        cartRepo.save(cartItem);
        return new ResponseDTO("Added", cartDTO);
        //return new ResponseDTO("Added", cartDTO);
        //userRepo.findById(); & bookById
    }*/
    @Override
    public ResponseDTO addCart(CartDTO cartDTO) {
        Optional<User> userOptional = userRepo.findById(cartDTO.getUserId());
        Optional<Book> bookOptional = bookRepo.findById(cartDTO.getBookId());

        if (userOptional.isEmpty()) {
            return new ResponseDTO("User not found with UserID :: " + cartDTO.getUserId(), null);
        }

        if (bookOptional.isEmpty()) {
            return new ResponseDTO("Book not found with BookID :: " + cartDTO.getBookId(), null);
        }

        User user = userOptional.get();
        Book book = bookOptional.get();

        Cart cartItem = new Cart(user, book, cartDTO.getQuantity());
        cartRepo.save(cartItem);

        return new ResponseDTO("Added", cartDTO);
    }


    @Override
    public String removeFromCartById(long cartId) {
        if(cartRepo.findById(cartId).isPresent()){
            cartRepo.deleteById(cartId);
            return "Cart with Id Deleted Successfully..";
        }
        return "Cart ID :: " + cartId + " Not Found..";
    }

    @Override
    public String removeAllCarts() {
            cartRepo.deleteAll();
            return "All Carts Deleted Successfully..";
        }



    @Override
    public ResponseDTO updateQuantity(String token, long cartId, long quantity) {
        long userId = jwt_token.decodeToken(token);
        Cart cartData = cartRepo.findById(cartId).orElseThrow();
        if(cartData.getUser().getUserId() == userId){
            cartData.setQuantity(quantity);
            cartRepo.save(cartData);
            return new ResponseDTO("Updated Successfully..",cartData);
        }
        return new ResponseDTO("Error while Updating",null);
    }

    @Override
    public List<Cart> findCartItemsByToken(String token) {//findByuserId cartRepo.userId
        long userId = jwt_token.decodeToken(token);
        return cartRepo.findByUserId(userId);
    }

    @Override
    public List<Cart> findAllCarts() {
        return cartRepo.findAll();
    }
}
