package cc.lovecode.util;

import cc.lovecode.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.Date;

public class JWTUtils {

    private final static String SECRET = "lovecode";

    private JWTUtils() {
    }

    public static String generateToken(User user) {
        Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 3600 * 24))
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .sign(algorithmHS);
    }

    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
            JWT.decode(token);
            return true;
        } catch (JWTDecodeException ignore) {
            return false;
        }
    }
}
