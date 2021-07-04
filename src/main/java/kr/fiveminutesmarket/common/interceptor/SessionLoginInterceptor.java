package kr.fiveminutesmarket.common.interceptor;

import kr.fiveminutesmarket.common.annotation.Auth;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.AuthenticationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 모든 handler가 method 타입이 아닐수도 있다.
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        Auth authAnnotation = ((HandlerMethod)handler).getMethodAnnotation(Auth.class);

        if (authAnnotation == null) {
            return true;
        }

        if (request.getSession().getAttribute("user") == null) {
            throw new AuthenticationException("로그인이 필요합니다.");
        }

        if (authAnnotation.seller()) {
            isSeller(request);
        }

        return true;
    }

    private void isSeller(HttpServletRequest request) {
        UserSessionDto userSession = (UserSessionDto)request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null ||
                !userSession.getSeller()) {
            throw new AuthenticationException("판매자 권한이 필요합니다.");
        }
    }
}
