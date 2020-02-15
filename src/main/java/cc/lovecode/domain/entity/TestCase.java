package cc.lovecode.domain.entity;

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
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "testcase")
public class TestCase extends BaseEntity {
    private String inputFileId;

    private String inputFilename;

    private String outputFileId;

    private String outputFilename;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
}
