package com.example.Book_Store_Application.exception;

import com.example.Book_Store_Application.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(data->data.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception Handling while REST API call", errorMessage);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        String errorMessage = exception.getMessage();
        ResponseDTO responseDTO = new ResponseDTO("Invalid Input From User", errorMessage);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCustomException.class)
    public ResponseEntity<ResponseDTO> handleUserIDNotFoundException(UserCustomException exception){
        ResponseDTO responseDTO = new ResponseDTO("Exception Handling while REST API call", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BookCustomException.class)
    public ResponseEntity<ResponseDTO> handleUserIDNotFoundException(BookCustomException exception){
        ResponseDTO responseDTO = new ResponseDTO("Exception Handling while REST API call", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(CartCustomException.class)
    public ResponseEntity<ResponseDTO> handleUserIDNotFoundException(CartCustomException exception){
        ResponseDTO responseDTO = new ResponseDTO("Exception Handling while REST API call", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
