package cc.lovecode.exception;

import cc.lovecode.enums.ErrorCode;

import java.util.Objects;

public class FileUploadException extends APIException {
    private String msg;
    public FileUploadException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        if (Objects.isNull(msg)) {
            return super.getMessage();
        } else {
            return msg;
        }
    }

    @Override
    public ErrorCode getCode() {
        return ErrorCode.FILE_UPLOAD_UNSUCCESSFULLY;
    }
}
