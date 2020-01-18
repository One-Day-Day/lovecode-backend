package cc.lovecode.domain.entity;

import cc.lovecode.dto.request.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.DigestUtils;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    private String username;

    private String password;

    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Problem> problems;

    public static User from(CreateUserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .password(DigestUtils.md5DigestAsHex(request.getPassword().getBytes()))
                .build();
    }
}
