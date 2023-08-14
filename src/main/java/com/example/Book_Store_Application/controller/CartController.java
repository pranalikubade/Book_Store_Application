package com.example.Book_Store_Application.controller;

import com.example.Book_Store_Application.dto.CartDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.model.Cart;
import com.example.Book_Store_Application.service.CartService_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService_Interface iCartServices;

    @PostMapping("addToCart")
    public ResponseEntity<ResponseDTO> addItemsToCart(@RequestBody CartDTO cartDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Book Successfully Added to Cart", iCartServices.addCart(cartDTO)), HttpStatus.OK);
    }

    @PutMapping ("removeCartById")
    public ResponseEntity<ResponseDTO> removeFromCartById(@RequestParam long cartId) {
        String cart = iCartServices.removeFromCartById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Cart  With ID  Deleted Successfully:: " + cartId, cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping ("removeAllCart")
    public String removeAllCarts(){
        return iCartServices.removeAllCarts();
    }

    @PutMapping("updateCart/{cartId}")//Request Header
    public ResponseEntity<ResponseDTO> updateQuantity(@RequestHeader String token, @PathVariable long cartId, @RequestParam long quantity){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Updated Details", iCartServices.updateQuantity(token,cartId,quantity)), HttpStatus.ACCEPTED);
    }

    @GetMapping("getAllCart")
    public List<Cart> findAllCarts(){
        return iCartServices.findAllCarts();
    }

    @GetMapping("getCartItemByToken")
    /*public List<Cart> findCartItemsByToken(@RequestHeader String token){
        return iCartServices.findCartItemsByToken(token);*/
    public ResponseEntity<ResponseDTO> findCartItemsByToken(@RequestParam String token) {
        List<Cart> cart = iCartServices.findCartItemsByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart with token fetched Successfully:: " + token, cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
