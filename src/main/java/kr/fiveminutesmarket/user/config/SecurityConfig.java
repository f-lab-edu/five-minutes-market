package kr.fiveminutesmarket.user.config;

import kr.fiveminutesmarket.user.security.JavaPasswordEncoder;
import kr.fiveminutesmarket.user.security.SHA256PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public JavaPasswordEncoder javaPasswordEncoder() {
        return new SHA256PasswordEncoder();
    }
}
