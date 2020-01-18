package cc.lovecode.jwt;

import cc.lovecode.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Optional;

public class JWTUtils {

    private final static String SECRET = "lovecode";
    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_USERNAME = "username";

    private JWTUtils() {
    }

    public static String generateToken(User user) {
        Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 3600 * 24))
                .withClaim(CLAIM_USER_ID, user.getId())
                .withClaim(CLAIM_USERNAME, user.getUsername())
                .sign(algorithmHS);
    }

    public static JWTUser parseJWTUser(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            JWTUser.JWTUserBuilder builder = JWTUser.builder();
            getClaim(CLAIM_USER_ID, decodedJWT).ifPresent(claim -> builder.id(claim.asLong()));
            getClaim(CLAIM_USERNAME, decodedJWT).ifPresent(claim -> builder.username(claim.asString()));
            return builder.build();
        } catch (JWTDecodeException ex) {
            return null;
        }
    }

    private static Optional<Claim> getClaim(String claimName, DecodedJWT decodedJWT) {
        Claim claim = decodedJWT.getClaim(claimName);
        return Optional.ofNullable(claim.isNull() ? null : claim);
    }
}
