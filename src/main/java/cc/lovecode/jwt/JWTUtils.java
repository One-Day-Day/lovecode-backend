package cc.lovecode.jwt;

import cc.lovecode.domain.entity.User;
import cc.lovecode.domain.entity.UserRole;
import cc.lovecode.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JWTUtils {

    private final static String SECRET = "lovecode";
    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_USERNAME = "username";
    private static final String CLAIM_ROLES = "roles";

    private JWTUtils() {
    }

    public static String generateToken(User user) {
        Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
        String[] roles = user.getRoles().stream()
                .map(UserRole::getRole)
                .map(Enum::name)
                .toArray(String[]::new);

        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 3600 * 24))
                .withClaim(CLAIM_USER_ID, user.getId())
                .withClaim(CLAIM_USERNAME, user.getUsername())
                .withArrayClaim(CLAIM_ROLES, roles)
                .sign(algorithmHS);
    }

    public static Optional<JWTUser> parseJWTUser(String token, Function<Long, List<UserRole>> queryRolesById) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            JWTUser.JWTUserBuilder builder = JWTUser.builder();
            getClaim(CLAIM_USER_ID, decodedJWT)
                    .ifPresent(claim -> builder.id(claim.asLong()));
            getClaim(CLAIM_USERNAME, decodedJWT)
                    .ifPresent(claim -> builder.username(claim.asString()));
            JWTUser jwtUser = builder.build();
            List<Role> roles = queryRolesById.apply(jwtUser.getId())
                    .stream()
                    .map(UserRole::getRole)
                    .collect(Collectors.toList());
            jwtUser.setRoles(roles);
            return Optional.of(jwtUser);
        } catch (JWTDecodeException ex) {
            return Optional.empty();
        }
    }

    private static Optional<Claim> getClaim(String claimName, DecodedJWT decodedJWT) {
        Claim claim = decodedJWT.getClaim(claimName);
        return Optional.ofNullable(claim.isNull() ? null : claim);
    }
}
