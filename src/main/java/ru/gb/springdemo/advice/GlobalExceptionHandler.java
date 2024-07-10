package ru.gb.springdemo.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Component
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> notFoundException(NoSuchElementException e){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> notFoundException(ResponseStatusException e){
        log.info(e.getMessage());
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }
}
