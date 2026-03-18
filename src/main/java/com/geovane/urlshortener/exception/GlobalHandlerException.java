package com.geovane.urlshortener.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UrlNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
