package com.zb.loanproject.exception;

import static com.zb.loanproject.type.ErrorCode.INVALID_REQUEST;

import com.zb.loanproject.dto.ErrorResponse;
import com.zb.loanproject.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // TODO : 나중엔 세분화 필요할듯?
    @ExceptionHandler(GlobalException.class)
    public ErrorResponse handleGlobalException(GlobalException e) {
        log.error("{} is occurred", e.getErrorCode());

        return ErrorResponse.builder()
                            .errorCode(e.getErrorCode())
                            .errorMessage(e.getErrorMessage())
                            .build();
    }

    // DB 관련 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException is occurred", e);

        return ErrorResponse.builder()
                            .errorCode(INVALID_REQUEST)
                            .errorMessage(INVALID_REQUEST.getDescription())
                            .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // DB 관련 에러
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException is occurred", e);

        return ErrorResponse.builder()
                            .errorCode(INVALID_REQUEST)
                            .errorMessage(INVALID_REQUEST.getDescription())
                            .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error("Exception is occurred", e);

        return ErrorResponse.builder()
                            .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                            .errorMessage(ErrorCode.INTERNAL_SERVER_ERROR.getDescription())
                            .build();
    }
}
