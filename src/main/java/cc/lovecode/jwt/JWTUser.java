package cc.lovecode.jwt;

import cc.lovecode.enums.Role;
import cc.lovecode.exception.AccessDeniedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTUser {
    private long id;
    private String username;
    private List<Role> roles;

    public boolean hasRole(Role role) {
        return !Objects.isNull(roles) && roles.contains(role);
    }

    public boolean isSuperAdmin() {
        return hasRole(Role.SUPER_ADMIN);
    }

    public void validateRoles(Role ...roles) {
        Arrays.stream(roles)
                .forEach(role -> {
                    if (!hasRole(role)) {
                        throw new AccessDeniedException();
                    }
                });
    }
}
