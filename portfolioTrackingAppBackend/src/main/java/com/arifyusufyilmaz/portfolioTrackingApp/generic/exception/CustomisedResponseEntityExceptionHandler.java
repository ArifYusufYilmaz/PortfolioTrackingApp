package com.arifyusufyilmaz.portfolioTrackingApp.generic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest){
        String message = exception.getMessage();
        String detail = webRequest.getDescription(false);
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(message ,HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}
