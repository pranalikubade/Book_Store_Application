package com.example.Book_Store_Application.controller;

import com.example.Book_Store_Application.dto.OrderDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.service.OrderService_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderCart")
public class OrderController {
    @Autowired
    private OrderService_Interface iOrderServices;

    @PostMapping("placeOrder/{token}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable String token,@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Order Details..", iOrderServices.placeOrder(token,orderDTO)), HttpStatus.ACCEPTED);
    }

    @GetMapping("getAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrdersList(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(":: All-Order ::", iOrderServices.getAllOrders()), HttpStatus.FOUND);
    }

    @PutMapping("cancelOrder/{token}")
    public String cancelOrder(@PathVariable String token,@RequestParam long orderId){
        return iOrderServices.cancelOrder(token,orderId);
    }

    @GetMapping("getOrdersByUserToken/{token}")
    public  ResponseEntity<ResponseDTO> getAllOrderForUser(@PathVariable String token){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Your Order Details..",iOrderServices.getAllOrderForUser(token)), HttpStatus.FOUND);
    }


}
