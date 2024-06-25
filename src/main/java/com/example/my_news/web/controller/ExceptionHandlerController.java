package com.example.my_news.web.controller;

import com.example.my_news.exception.AlreadyExistsException;
import com.example.my_news.exception.EntityNotFoundException;
import com.example.my_news.web.model.single.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(e.getLocalizedMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(e.getLocalizedMessage()));
    }

}
