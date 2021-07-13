package kr.fiveminutesmarket.common.config;

import kr.fiveminutesmarket.common.interceptor.SessionExtensionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionConfig implements WebMvcConfigurer {

    private final SessionExtensionInterceptor sessionExtensionInterceptor;

    public SessionConfig(SessionExtensionInterceptor sessionExtensionInterceptor) {
        this.sessionExtensionInterceptor = sessionExtensionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionExtensionInterceptor);
    }
}
