package kr.fiveminutesmarket.common.security;

import kr.fiveminutesmarket.common.annotation.LoginUser;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.errors.AuthenticationException;
import kr.fiveminutesmarket.common.exception.errors.TokenNotExistedException;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SecurityUserResolver implements HandlerMethodArgumentResolver {
    private final RedisAuthUtils redisAuthUtils;

    public SecurityUserResolver(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String bearerValue = webRequest.getHeader("Authorization");

        if (bearerValue == null) {
            throw new TokenNotExistedException();
        }

        UserSessionDto sessionDto = redisAuthUtils.getSession(bearerValue.substring(7));

        if(sessionDto == null) {
            throw new AuthenticationException("로그인이 필요합니다.");
        }

        return sessionDto;
    }
}
