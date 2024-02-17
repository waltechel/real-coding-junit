package com.realcoding.chapter02.api.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // 2xx Success
    OK("성공적인 요청입니다.", HttpStatus.OK),
    CREATED("자원이 성공적으로 생성되었습니다.", HttpStatus.CREATED),
    ACCEPTED("요청이 수락되었지만 처리는 완료되지 않았습니다.", HttpStatus.ACCEPTED),

    // 4xx Client Error
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("인증되지 않은 요청입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("접근이 금지되었습니다.", HttpStatus.FORBIDDEN),
    NOT_FOUND("자원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 5xx Server Error
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_IMPLEMENTED("서버가 요청된 기능을 지원하지 않습니다.", HttpStatus.NOT_IMPLEMENTED),
    BAD_GATEWAY("게이트웨이 오류가 발생했습니다.", HttpStatus.BAD_GATEWAY),

    // ... 기타 필요한 HTTP 상태 코드 추가 ...

    // 사용자 정의 오류
    INVALID_ACCESS_TOKEN("유효하지 않은 액세스 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_REFRESH_TOKEN("유효하지 않은 리프레시 토큰입니다.", HttpStatus.UNAUTHORIZED),
    NOT_EXIST_USER("존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED);
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
