package cc.lovecode.domain.entity;

import cc.lovecode.dto.request.CreateProblemRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Problem extends BaseEntity {
    private String name;
    private String description;
    private String hint;
    private String inputDescription;
    private String outputDescription;
    private String sampleInput;
    private String sampleOutput;

    private Long timeLimit;
    private Long memoryLimit;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public static Problem from(CreateProblemRequest request) {
        return Problem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .hint(request.getHint())
                .inputDescription(request.getInputDescription())
                .outputDescription(request.getOutputDescription())
                .sampleInput(request.getSampleInput())
                .sampleOutput(request.getSampleOutput())
                .timeLimit(request.getTimeLimit())
                .memoryLimit(request.getMemoryLimit())
                .build();
    }
}
