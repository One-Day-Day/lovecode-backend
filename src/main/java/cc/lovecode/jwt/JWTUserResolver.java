package cc.lovecode.jwt;

import cc.lovecode.domain.repository.UserRoleRepository;
import cc.lovecode.exception.UnauthorizedAccessException;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class JWTUserResolver implements HandlerMethodArgumentResolver {
    public static final String JWT_TOKEN_HEADER_NAME = "Authorization";
    public static final String PREFIX = "Bearer ";

    private UserRoleRepository userRoleRepository;

    public JWTUserResolver(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

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
        Optional<JWTUser> jwtUser = JWTUtils.parseJWTUser(token, userId -> userRoleRepository.findAllByUserId(userId));
        return jwtUser.orElseThrow(UnauthorizedAccessException::new);
    }
}
