package moe.overnight.my_sebastian.common.exception;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Map<String, Object> details; // 추가 컨텍스트(필드, 파라미터 등)
    private final Object[] messageArgs;        // i18n 파라미터

    public ApiException(ErrorCode errorCode) {
        this(errorCode, null, null, null);
    }

    public ApiException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, null, cause);
    }

    public ApiException(ErrorCode errorCode, Map<String, Object> details) {
        this(errorCode, details, null, null);
    }

    public ApiException(ErrorCode errorCode, Map<String, Object> details, Object[] messageArgs, Throwable cause) {
        super(Objects.requireNonNull(errorCode, "errorCode").defaultMessage(), cause);
        this.errorCode = errorCode;
        this.details = details;
        this.messageArgs = messageArgs;
    }
}
