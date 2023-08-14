package com.example.Book_Store_Application.controller;

import com.example.Book_Store_Application.dto.*;
import com.example.Book_Store_Application.service.UserService_Interface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService_Interface iUserServices;

    @PostMapping("register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Registered Successfully...", iUserServices.registerUser(userDTO)), HttpStatus.CREATED);
    }

    @PostMapping("login")//get to post
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return new ResponseEntity<String>(iUserServices.loginUser(loginDTO.getEmail(),loginDTO.getPassword()), HttpStatus.ACCEPTED);
    }


    @PutMapping("updateUser/{id}")
    public ResponseEntity<ResponseDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("User Details Updated Successfully..", iUserServices.updateUserById(userDTO,id));
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("User Details", iUserServices.getUserById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")//by id
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("User deleted successfully", iUserServices.deleteUserById(Long.valueOf((String.valueOf(id)))));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
    @PutMapping("verifyToken/{token}")
    public String verifyUsingToken(@PathVariable String token){
        return iUserServices.verifyUsingToken(token);
    }
}
