package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public class ObjectDoesAlreadyExistsException extends APIException {
    private ErrorCode errorCode;

    public ObjectDoesAlreadyExistsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCode getCode() {
        return errorCode;
    }
}
