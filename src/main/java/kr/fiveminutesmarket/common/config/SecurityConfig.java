package kr.fiveminutesmarket.common.config;

import kr.fiveminutesmarket.common.security.JavaPasswordEncoder;
import kr.fiveminutesmarket.common.security.SHA256PasswordEncoder;
import kr.fiveminutesmarket.common.security.SecurityUserResolver;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final RedisAuthUtils redisAuthUtils;

    public SecurityConfig(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Bean
    public JavaPasswordEncoder javaPasswordEncoder() {
        return new SHA256PasswordEncoder();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SecurityUserResolver(redisAuthUtils));
    }
}
