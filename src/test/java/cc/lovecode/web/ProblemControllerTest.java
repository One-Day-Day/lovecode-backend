package cc.lovecode.web;

import cc.lovecode.BaseTest;
import cc.lovecode.domain.entity.Problem;
import cc.lovecode.dto.request.CreateProblemRequest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProblemControllerTest extends BaseTest {
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
                .expect(jsonPath("$.name").value("title 0"))
                .expect(jsonPath("$.description").value("description"))
                .expect(jsonPath("$.inputDescription").value("input description"))
                .expect(jsonPath("$.outputDescription").value("output description"))
                .expect(jsonPath("$.sampleInput").value("sample input"))
                .expect(jsonPath("$.sampleOutput").value("sample output"))
                .expect(jsonPath("$.memoryLimit").value(1024))
                .expect(jsonPath("$.timeLimit").value(1000))
                .expect(jsonPath("$.hint").value("hint"));
    }

    @Test
    void should_create_problem_successfully() {
        CreateProblemRequest request = CreateProblemRequest.builder()
                .name("name")
                .description("description")
                .hint("hint")
                .inputDescription("input description")
                .outputDescription("output description")
                .memoryLimit(1024L)
                .timeLimit(1024L)
                .sampleInput("sample input")
                .sampleOutput("sample output")
                .build();

        given()
                .body(request)
                .when()
                .post("/api/problems")
                .then()
                .expect(status().isCreated())
                .expect(jsonPath("$.id").isNumber())
                .expect(jsonPath("$.name").value("name"))
                .expect(jsonPath("$.description").value("description"))
                .expect(jsonPath("$.hint").value("hint"))
                .expect(jsonPath("$.inputDescription").value("input description"))
                .expect(jsonPath("$.outputDescription").value("output description"))
                .expect(jsonPath("$.timeLimit").value(1024))
                .expect(jsonPath("$.memoryLimit").value(1024))
                .expect(jsonPath("$.sampleInput").value("sample input"))
                .expect(jsonPath("$.sampleOutput").value("sample output"));
    }

    private void prepareProblems() {
        List<Problem> problems = IntStream.range(0, 3)
                .mapToObj(i -> {
                    return Problem.builder()
                            .name("title " + i)
                            .description("description")
                            .hint("hint")
                            .inputDescription("input description")
                            .outputDescription("output description")
                            .sampleInput("sample input")
                            .sampleOutput("sample output")
                            .timeLimit(1000L)
                            .memoryLimit(1024L)
                            .owner(defaultUser)
                            .build();
                })
                .collect(Collectors.toList());
        problemRepository.saveAll(problems);
    }
}
