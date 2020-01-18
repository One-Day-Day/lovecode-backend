package cc.lovecode.jwt;

import cc.lovecode.exception.UnauthorizedAccessException;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

public class JWTUserResolver implements HandlerMethodArgumentResolver {
    public static final String JWT_TOKEN_HEADER_NAME = "Authorization";
    public static final String PREFIX = "Bearer ";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(JWTUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = Optional.ofNullable(webRequest.getHeader(JWT_TOKEN_HEADER_NAME))
                .map(v -> v.replace(PREFIX, ""))
                .orElse(null);
        if (StringUtils.isEmpty(token)) {
            throw new UnauthorizedAccessException();
        }
        JWTUser jwtUser = JWTUtils.parseJWTUser(token);
        if (Objects.isNull(jwtUser)) {
            throw new UnauthorizedAccessException();
        }
        return jwtUser;
    }
}
