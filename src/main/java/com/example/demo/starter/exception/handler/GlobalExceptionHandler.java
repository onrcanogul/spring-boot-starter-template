package com.example.demo.starter.exception.handler;

import com.example.demo.starter.exception.BadRequestException;
import com.example.demo.starter.exception.ForbiddenException;
import com.example.demo.starter.exception.NotFoundException;
import com.example.demo.starter.exception.UnauthorizedException;
import com.example.demo.starter.util.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ServiceResponse<String>> handleNotFoundException(NotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ServiceResponse.failure(ex.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ServiceResponse<String>> handleBadRequestException(BadRequestException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ServiceResponse.failure(ex.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ServiceResponse<String>> handleUnauthorizedException(UnauthorizedException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ServiceResponse.failure(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ServiceResponse<String>> handleForbiddenException(ForbiddenException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ServiceResponse.failure(ex.getMessage(), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }

}
