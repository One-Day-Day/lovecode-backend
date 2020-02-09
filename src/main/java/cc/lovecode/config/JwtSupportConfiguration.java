package cc.lovecode.config;

import cc.lovecode.domain.repository.UserRoleRepository;
import cc.lovecode.jwt.JWTUserResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ConditionalOnWebApplication
public class JwtSupportConfiguration implements WebMvcConfigurer {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new JWTUserResolver(userRoleRepository));
    }
}
