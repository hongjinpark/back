package com.example.back.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidErrorCode {

    NOT_EMAIL_FORM(400, "이메일 형식이 아닙니다."),
    EMPTY_PARAMETER(400, "필수 값이 누락되었습니다."),
    MIN_VALUE(400, "최소값보다 커야 합니다.")
    ;


    private int status;
    private String errorCode;
}
