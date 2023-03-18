package com.yeonhee.blog.response;

import com.yeonhee.blog.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseModel<E> {
    private boolean success;

    private String message;

    private String code;

    private E data;

    public static <E> ApiResponseModel<E> success(E data) {
        return ApiResponseModel.<E>builder()
                .success(true)
                .data(data)
                .build();
    }

    public static <E> ApiResponseModel<E> error(ErrorCode errorCode) {
        return ApiResponseModel.<E>builder()
                .success(false)
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();
    }
}
