package moe.overnight.my_sebastian.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode{
    String code();
    HttpStatus httpStatus();
    String defaultMessage();
}
