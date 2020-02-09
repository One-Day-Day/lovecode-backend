package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public class AccessDeniedException extends APIException {
    @Override
    public ErrorCode getCode() {
        return ErrorCode.ACCESS_DENIED;
    }
}
