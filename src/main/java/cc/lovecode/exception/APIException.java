package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public abstract class APIException extends RuntimeException {
    public abstract ErrorCode getCode();

    @Override
    public String getMessage() {
        return getCode().getMessage();
    }
}
