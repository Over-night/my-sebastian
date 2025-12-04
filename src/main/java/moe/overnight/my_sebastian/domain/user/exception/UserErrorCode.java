package moe.overnight.my_sebastian.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.overnight.my_sebastian.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USR_INVALID_USER_ID("USR-001", HttpStatus.BAD_REQUEST, "유효하지 않은 유저 ID"),
    USR_INVALID_USER_ROLE("USR-002", HttpStatus.BAD_REQUEST, "유효하지 않은 유저 권한"),

    USR_PASSWORD_INCORRECT("USR-101", HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않음");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }

    @Override
    public String defaultMessage() {
        return this.message;
    }
}