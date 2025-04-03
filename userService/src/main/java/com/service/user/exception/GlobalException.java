package com.service.user.exception;


import com.service.user.payload.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> ResourceNotException(ResourceNotFoundException ex){

       String message =  ex.getMessage();

      ExceptionResponse exception =  new ExceptionResponse();
      exception.setMessage(message);
      exception.setSuccess(true);
      exception.setStatus(HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);

    }
}
