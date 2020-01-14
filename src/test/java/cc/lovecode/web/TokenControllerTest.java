package cc.lovecode.web;

import cc.lovecode.BaseTest;
import cc.lovecode.dto.LoginRequest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TokenControllerTest extends BaseTest {
    @Test
    void should_return_a_token_when_login_succeed() {
        LoginRequest request = new LoginRequest(defaultUsername, defaultPassword);
        given()
                .body(request)
                .post("/tokens")
                .then()
                .expect(status().isOk())
                .expect(jsonPath("$.token").hasJsonPath());
    }
}
