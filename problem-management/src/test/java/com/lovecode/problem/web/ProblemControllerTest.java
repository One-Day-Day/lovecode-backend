package com.lovecode.problem.web;

import com.lovecode.problem.BaseTest;
import com.lovecode.problem.domain.Problem;
import com.lovecode.problem.domain.repository.ProblemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProblemControllerTest extends BaseTest {
    @Autowired
    private ProblemRepository problemRepository;

    @Test
    void should_get_fist_page_of_summary_problems() {
        prepareProblems();
        given().when()
                .get("/api/problems?page=0&size=2")
                .then()
                .expect(status().isOk())
                .expect(jsonPath("$.totalNumbers").value(3))
                .expect(jsonPath("$.totalPages").value(2))
                .expect(jsonPath("$.data[0].title").value("title 2"))
                .expect(jsonPath("$.data[1].title").value("title 1"));
    }

    @Test
    void should_get_last_page_of_summary_problems() {
        prepareProblems();
        given().when()
                .get("/api/problems?page=1&size=2")
                .then()
                .expect(status().isOk())
                .expect(jsonPath("$.totalNumbers").value(3))
                .expect(jsonPath("$.totalPages").value(2))
                .expect(jsonPath("$.data[0].title").value("title 0"));
    }

    @Test
    void should_return_the_right_problem_when_get_an_existing_problem() {
        prepareProblems();
        given().when()
                .get("/api/problems/1000")
                .then()
                .expect(status().isOk())
                .expect(jsonPath("$.title").value("title 0"))
                .expect(jsonPath("$.description").value("description"))
                .expect(jsonPath("$.inputDescription").value("input description"))
                .expect(jsonPath("$.outputDescription").value("output description"))
                .expect(jsonPath("$.sampleInput").value("sample input"))
                .expect(jsonPath("$.sampleOutput").value("sample output"))
                .expect(jsonPath("$.memoryLimit").value(1024))
                .expect(jsonPath("$.timeLimit").value(1000));
    }

    private void prepareProblems() {
        List<Problem> problems = IntStream.range(0, 3)
                .mapToObj(i -> {
                    return Problem.builder()
                            .title("title " + i)
                            .description("description")
                            .inputDescription("input description")
                            .outputDescription("output description")
                            .sampleInput("sample input")
                            .sampleOutput("sample output")
                            .timeLimit(1000L)
                            .memoryLimit(1024L)
                            .build();
                })
                .collect(Collectors.toList());
        problemRepository.saveAll(problems);
    }
}
