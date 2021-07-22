package kr.fiveminutesmarket.common.security;

import kr.fiveminutesmarket.common.annotation.AuthUser;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SecurityUserResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUserResolver.class);

    private final RedisAuthUtils redisAuthUtils;

    public SecurityUserResolver(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(AuthUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        String bearerValue = webRequest.getHeader("Authorization");

        logger.info("########## Authentication Parameter Object Injection ##########");

        if (bearerValue != null) {
            return redisAuthUtils.getSession(bearerValue.substring(7));
        }

        return null;
    }
}
