package kr.fiveminutesmarket.common.interceptor;

import kr.fiveminutesmarket.common.annotation.Authentication;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.errors.AuthenticationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 모든 handler가 method 타입이 아닐수도 있다.
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        Authentication authAnnotation = ((HandlerMethod)handler).getMethodAnnotation(Authentication.class);

        Optional<UserSessionDto> userSession
                = Optional.ofNullable((UserSessionDto) request.getAttribute("authSession"));

        if (authAnnotation != null) {
            userSession.orElseThrow(() -> new AuthenticationException("로그인이 필요합니다."));

            isSeller(userSession.get());
        }

        return true;
    }

    private void isSeller(UserSessionDto userSession) {
        if (userSession.getSeller() == null ||
                !userSession.getSeller()) {
            throw new AuthenticationException("판매자 권한이 필요합니다.");
        }
    }
}
