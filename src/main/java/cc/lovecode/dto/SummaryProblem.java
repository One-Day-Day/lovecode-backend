package cc.lovecode.dto;

import cc.lovecode.domain.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummaryProblem {
    private Long id;
    private String title;
    private Long timeLimit;
    private Long memoryLimit;

    public static SummaryProblem from(Problem problem) {
        return SummaryProblem.builder()
                .id(problem.getId())
                .memoryLimit(problem.getMemoryLimit())
                .timeLimit(problem.getTimeLimit())
                .title(problem.getName())
                .build();
    }
}
