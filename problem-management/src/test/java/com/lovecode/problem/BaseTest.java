package com.lovecode.problem;

import com.lovecode.problem.repository.ProblemRepository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class BaseTest {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssuredMockMvc.mockMvc(mockMvc);
        truncate();
    }

    private void truncate() {
        problemRepository.deleteAll();
    }

    public MockMvcRequestSpecification given() {
        return RestAssuredMockMvc
                .given()
                .header("Accept", ContentType.JSON.withCharset("UTF-8"))
                .header("Content-Type", ContentType.JSON.withCharset("UTF-8"));
    }
}
