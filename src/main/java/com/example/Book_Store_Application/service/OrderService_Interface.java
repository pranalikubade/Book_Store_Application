package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.OrderDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;

public interface OrderService_Interface {
    ResponseDTO placeOrder(String token, OrderDTO orderDTO);
    String cancelOrder(String token, long orderId);
    ResponseDTO getAllOrders();
    ResponseDTO getAllOrderForUser(String token);
}

