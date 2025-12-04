package moe.overnight.my_sebastian.domain.user.exception;

import moe.overnight.my_sebastian.common.exception.ApiException;
import moe.overnight.my_sebastian.common.exception.ErrorCode;

public class InvalidUserException extends ApiException {
    public InvalidUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}