package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

public class IncorrectUsernameOrPasswordException extends APIException {
    @Override
    public ErrorCode getCode() {
        return ErrorCode.LOGIN_UNSUCCESSFULLY;
    }
}
