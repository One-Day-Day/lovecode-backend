package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public class ObjectNotFoundException extends APIException {
    private ErrorCode errorCode;

    public ObjectNotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    @Override
    public ErrorCode getCode() {
        return null;
    }
}
