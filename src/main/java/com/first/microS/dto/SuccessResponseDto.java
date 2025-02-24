package com.first.microS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SuccessResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorDateTime;
}
