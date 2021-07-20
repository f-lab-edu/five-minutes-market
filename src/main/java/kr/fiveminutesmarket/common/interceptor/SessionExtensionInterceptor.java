package kr.fiveminutesmarket.common.interceptor;

import kr.fiveminutesmarket.common.annotation.Authentication;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.errors.AuthenticationException;
import kr.fiveminutesmarket.common.exception.errors.TokenNotExistedException;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class SessionExtensionInterceptor implements HandlerInterceptor {

    private final RedisAuthUtils redisUtils;

    public SessionExtensionInterceptor(RedisAuthUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 모든 handler가 method 타입이 아닐수도 있다.
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        Authentication authAnnotation = ((HandlerMethod)handler).getMethodAnnotation(Authentication.class);

        Optional<String> bearerValue = Optional.ofNullable(request.getHeader("Authorization"));

        if (authAnnotation != null) {
            bearerValue.orElseThrow(TokenNotExistedException::new);

            Optional<UserSessionDto> userSession = Optional.ofNullable(redisUtils.getSession(bearerValue.get().substring(7)));
            userSession.orElseThrow(() -> new AuthenticationException("로그인이 필요합니다."));

            isSeller(userSession.get());
        }

        // bearerValue 존재하지 않을 때 세션 연장 로직 패스
        bearerValue.ifPresent(bearer -> {
            UserSessionDto sessionDto = redisUtils.getSession(bearer.substring(7));
            // 세션 만료시간 갱신
            redisUtils.renewSessionExpire(sessionDto, bearer);
        });

        return true;
    }

    private void isSeller(UserSessionDto userSession) {
        if (userSession.getSeller() == null ||
                !userSession.getSeller()) {
            throw new AuthenticationException("판매자 권한이 필요합니다.");
        }
    }
}
