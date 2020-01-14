package cc.lovecode.exception;

public class IncorrectUsernameOrPasswordException extends RuntimeException {
    @Override
    public String getMessage() {
        return "账号或密码错误";
    }
}
