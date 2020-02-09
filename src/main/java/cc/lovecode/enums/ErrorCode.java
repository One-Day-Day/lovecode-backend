package cc.lovecode.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    LOGIN_UNSUCCESSFULLY(HttpStatus.BAD_REQUEST, "账号或密码错误"),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "未登录"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "无权限访问"),
    DUPLICATED_USERNAME(HttpStatus.BAD_REQUEST, "用户名已使用"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "用户不存在"),
    USER_NOT_ACTIVE(HttpStatus.FORBIDDEN, "用户已禁用"),
    PROBLEM_NOT_FOUND(HttpStatus.NOT_FOUND, "问题不存在");

    private String message;
    private HttpStatus status;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
