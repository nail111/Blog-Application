package com.blog.exception;

import com.blog.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public final ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                                   WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BlogAPIException.class)
    public final ResponseEntity<?> handleBlogAPIException(BlogAPIException blogAPIException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                blogAPIException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, blogAPIException.getStatus());
    }

    // handle Validation Exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // handle Global exception
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
