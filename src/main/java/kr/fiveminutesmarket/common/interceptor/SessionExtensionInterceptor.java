package kr.fiveminutesmarket.common.interceptor;

import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SessionExtensionInterceptor implements HandlerInterceptor {

    private final RedisAuthUtils redisAuthUtils;

    public SessionExtensionInterceptor(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<String> bearerValue = Optional.ofNullable(request.getHeader("Authorization"));

        bearerValue.ifPresent(bearer -> {
            String bearerToken = bearer.substring(7);
            UserSessionDto userSession = redisAuthUtils.getSession(bearerToken);
            // 세션 만료시간 갱신
            redisAuthUtils.renewSessionExpire(userSession, bearerToken);
            // interceptor 전달을 위한 처리
            request.setAttribute("authSession", userSession);
        });

        return true;
    }


}
