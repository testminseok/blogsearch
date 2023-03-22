package com.software.blog.search.common.response;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class ApiResponse<T> {

    private final String timestamp = LocalDateTime.now().toString();

    private final UUID txId = UUID.randomUUID();

    private String error;

    private String message;
    
    private T data;


    private ApiResponse(String error, String message, T data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse<EmptyBody> toOkResponseEntity() {
        return toResponseEntity(ErrorCode.OK, EmptyBody.getInstance());
    }

    public static <T> ApiResponse<T> toOkResponseEntity(T data) {
        return toResponseEntity(ErrorCode.OK, data);
    }

    public static ApiResponse<EmptyBody> toErrResponseEntity(ErrorCode errorCode) {
        return toResponseEntity(errorCode, EmptyBody.getInstance());
    }

    public static ApiResponse<EmptyBody> toErrResponseEntity(ErrorCode errorCode,
                                                                                        String msg) {
        if (errorCode != null) {
            return toResponseEntity(errorCode, EmptyBody.getInstance());
        } else {
            return new ApiResponse<>(ErrorCode.MSG.getErrCd(), msg, EmptyBody.getInstance());
        }
    }

    public static <T> ApiResponse<T> toResponseEntity(ErrorCode errorCode, T data) {
        return new ApiResponse<>(errorCode.getErrCd(), errorCode.getErrMsg(), data);
    }
}
