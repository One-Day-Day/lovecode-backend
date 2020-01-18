package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public class UnauthorizedAccessException extends APIException {
    @Override
    public ErrorCode getCode() {
        return ErrorCode.UNAUTHORIZED_ACCESS;
    }
}
