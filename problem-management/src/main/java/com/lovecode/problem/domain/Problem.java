package com.lovecode.problem.domain;

import com.lovecode.problem.dto.CreateProblemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String hint;
    private String inputDescription;
    private String outputDescription;
    private String sampleInput;
    private String sampleOutput;

    private Long timeLimit;
    private Long memoryLimit;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

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
