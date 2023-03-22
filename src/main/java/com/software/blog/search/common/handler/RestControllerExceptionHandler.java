package com.software.blog.search.common.handler;

import com.software.blog.search.common.response.ApiResponse;
import com.software.blog.search.common.response.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
public class RestControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiResponse handleRuntimeException(RuntimeException e) {
        return ApiResponse.toErrResponseEntity(ErrorCode.SERVER_ERROR);
    }
}
