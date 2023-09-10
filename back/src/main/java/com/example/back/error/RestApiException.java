package com.example.back.error;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;

    public RestApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}