package com.example.back.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {



    NO_CONTENT(204, "COMMON-ERR-204", "No Content"),
    RESOURCE_NOT_FOUND(404,"COMMON-ERR-404","Resource Not exists"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","Internal Server Error"),
    BAD_REQUEST(400,"COMMON-ERR-400","Bad Request")
    ;

    private final int status;
    private final String errorCode;
    private final String message;

}