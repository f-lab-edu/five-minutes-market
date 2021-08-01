package kr.fiveminutesmarket.common.security;

import kr.fiveminutesmarket.common.annotation.LoginUser;
import kr.fiveminutesmarket.common.exception.errors.AuthenticationException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SecurityUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {


        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return Optional.ofNullable(request.getAttribute("authSession"))
                .orElseThrow(() -> new AuthenticationException("로그인이 필요합니다."));
    }
}
