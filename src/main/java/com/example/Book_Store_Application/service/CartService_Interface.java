package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.CartDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.model.Cart;

import java.util.List;

public interface CartService_Interface {
    ResponseDTO addCart(CartDTO cartDTO);

    String removeFromCartById(long cartId);
    String removeAllCarts();
    ResponseDTO updateQuantity(String token, long cartId, long quantity);
    List<Cart> findCartItemsByToken(String token);
    List<Cart> findAllCarts();
}
