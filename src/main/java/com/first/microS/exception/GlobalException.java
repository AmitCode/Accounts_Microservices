package com.first.microS.exception;

import com.first.microS.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    /**
     * Handle the {@link CustomerAlreadyExist} exception. This exception is thrown when there is an attempt to create
     * a customer with a phone number or email that already exists in the database.
     *
     * @param customerAlreadyExist the exception that is thrown
     * @param webRequest the request that triggered the exception
     * @return a {@link ResponseEntity} containing an {@link ErrorResponseDto} that contains the context path of the
     * request, the HTTP status code (400), the error message from the exception, and the current time.
     */
    @ExceptionHandler(CustomerAlreadyExist.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExist(CustomerAlreadyExist customerAlreadyExist
    , WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getContextPath(),
                HttpStatus.BAD_REQUEST,
                customerAlreadyExist.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ResourceNotFound ResourceNotFound
            , WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getContextPath(),
                HttpStatus.NOT_FOUND,
                ResourceNotFound.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
    }
}
