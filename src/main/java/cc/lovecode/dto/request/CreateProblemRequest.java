package cc.lovecode.dto.request;

import cc.lovecode.domain.entity.TestCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProblemRequest {
    private String name;
    private String description;
    private String hint;
    private String inputDescription;
    private String outputDescription;
    private String sampleInput;
    private String sampleOutput;
    private Long timeLimit;
    private Long memoryLimit;
    private List<TestCase> testCases;
}
