package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public class UserDoesNotActiveException extends APIException {
    @Override
    public ErrorCode getCode() {
        return ErrorCode.USER_NOT_ACTIVE;
    }
}
