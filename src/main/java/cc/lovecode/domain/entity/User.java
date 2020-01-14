package cc.lovecode.domain.entity;

import cc.lovecode.dto.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.DigestUtils;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

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

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public static User from(CreateUserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .password(DigestUtils.md5DigestAsHex(request.getPassword().getBytes()))
                .build();
    }
}
