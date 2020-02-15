package cc.lovecode;

import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.entity.UserRole;
import cc.lovecode.domain.repository.ProblemRepository;
import cc.lovecode.domain.repository.UserRepository;
import cc.lovecode.enums.Role;
import cc.lovecode.jwt.JWTUserResolver;
import cc.lovecode.jwt.JWTUtils;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class BaseTest {
    @Autowired
    protected ProblemRepository problemRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    protected User defaultUser;
    protected final String defaultUsername = "TEST";
    protected final String defaultPassword = "TEST";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssuredMockMvc.mockMvc(mockMvc);
        truncate();
        UserRole userRole = new UserRole(null, Role.SUPER_ADMIN);
        defaultUser = User
                .builder()
                .username(defaultUsername)
                .password(DigestUtils.md5DigestAsHex(defaultPassword.getBytes()))
                .active(true)
                .roles(Arrays.asList(userRole))
                .build();
        userRole.setUser(defaultUser);
        userRepository.save(defaultUser);
    }

    private void truncate() {
        problemRepository.deleteAll();
        userRepository.deleteAll();
    }

    public MockMvcRequestSpecification given() {
        return RestAssuredMockMvc
                .given()
                .header(JWTUserResolver.JWT_TOKEN_HEADER_NAME, getAuthorization())
                .header("Accept", ContentType.JSON.withCharset("UTF-8"))
                .header("Content-Type", ContentType.JSON.withCharset("UTF-8"));
    }

    private String getAuthorization() {
        return JWTUserResolver.PREFIX + JWTUtils.generateToken(defaultUser);
    }
}
