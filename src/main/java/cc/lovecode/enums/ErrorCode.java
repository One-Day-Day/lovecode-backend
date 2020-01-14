package cc.lovecode.enums;

public enum ErrorCode {
    LOGIN_UNSUCCESSFULLY("账号或密码错误");

    private String message;

    ErrorCode(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
