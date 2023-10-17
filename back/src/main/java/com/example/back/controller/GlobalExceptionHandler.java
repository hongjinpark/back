package com.example.back.controller;


import com.example.back.dto.ErrorResponse;
import com.example.back.error.ErrorCode;
import com.example.back.error.ValidErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외처리 클래스
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException e){
        log.error("부적절한 인자값 에러", e);
        e.printStackTrace();
        ErrorResponse response = new ErrorResponse(ErrorCode.RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException2(MethodArgumentNotValidException e){
        log.error("파라미터 없음", e);
        e.printStackTrace();
        ErrorResponse response = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception e){
        log.error("handleException", e);
        e.printStackTrace();
        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult) {
        int status = 0;
        String errorCode = "";
        String message = "";

        if(bindingResult.hasErrors()) {

            //Dto에 설정한 message 값을 가져온다.
            message = bindingResult.getFieldError().getDefaultMessage();

            //Dto에 유효성 체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode) {
                case "Email":
                    errorCode = ValidErrorCode.NOT_EMAIL_FORM.getErrorCode();
                    status = ValidErrorCode.NOT_EMAIL_FORM.getStatus();
                    break;
                case "NotEmpty":
                    errorCode = ValidErrorCode.EMPTY_PARAMETER.getErrorCode();
                    status = ValidErrorCode.EMPTY_PARAMETER.getStatus();
                    break;
                case "Min":
                    errorCode = ValidErrorCode.MIN_VALUE.getErrorCode();
                    status = ValidErrorCode.MIN_VALUE.getStatus();
                    break;
                case "NotNull":
                    errorCode = ValidErrorCode.NULL_PARAMETER.getErrorCode();
                    status = ValidErrorCode.NULL_PARAMETER.getStatus();
                    break;
            }
        }

        return new ErrorResponse(status, errorCode, message);
    }
}
