package cc.lovecode.web;

import cc.lovecode.BaseTest;
import cc.lovecode.dto.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TokenControllerTest extends BaseTest {
    @Test
    void should_return_a_token_when_login_succeed() {
        given()
                .body(buildLoginRequest())
                .post("/tokens")
                .then()
                .expect(status().isOk())
                .expect(jsonPath("$.token").hasJsonPath());
    }

    private LoginRequest buildLoginRequest() {
        return new LoginRequest(defaultUsername, defaultPassword);
    }

    @Test
    void should_return_incorrect_username_or_password_message_and_with_400_status_code_when_login_failed() {
        given()
                .body(new LoginRequest("incorrect user", "incorrect pwd"))
                .post("/tokens")
                .then()
                .expect(status().isBadRequest())
                .expect(jsonPath("$.code").value("LOGIN_UNSUCCESSFULLY"))
                .expect(jsonPath("$.message").value("账号或密码错误"));
    }
}
