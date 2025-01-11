package com.example.restaurantreservationaa.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorResponse {

    private int code;
    private String message;
    private Map<String, String> errorMessages;

    private ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ErrorResponse(Map<String, String> errorMessages) {
        this.code = 400;
        this.message = "Bad Request";
        this.errorMessages = errorMessages;
    }

    public static ErrorResponse generalError(int code, String message) {
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse validationError(Map<String, String> errors) {
        return new ErrorResponse(errors);
    }
}
