package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.dto.UserDTO;

public interface UserService_Interface {
    public ResponseDTO registerUser(UserDTO userDTO);
    public String loginUser(String email,String password);

    // :: UserBook - Get User Details Using  ID :: //
   public ResponseDTO getUserById(long id);

    // :: UserBook - Update User Details Using ID :: //
    ResponseDTO updateUserById(UserDTO userDTO, Long userId);
    ResponseDTO deleteUserById(Long userId);
    String verifyUsingToken(String token);
}