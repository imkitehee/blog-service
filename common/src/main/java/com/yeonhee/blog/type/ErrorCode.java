package com.yeonhee.blog.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE("1001", 422, "입력 값이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR("1002", 500, "서버에 오류가 발생하여 요청을 수행하지 못했습니다."),
    NOT_FOUND_DATA("1003", 404, "데이터가 존재하지 않습니다."),
    ;

    private final String code;
    private final int status;
    private final String message;
}
