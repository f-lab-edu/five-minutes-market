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
            UserSessionDto userSession = redisAuthUtils.getSession(bearer.substring(7));
            // 세션 만료시간 갱신
            redisAuthUtils.renewSessionExpire(userSession, bearer.substring(7));
            // interceptor 전달을 위한 처리
            request.setAttribute("authSession", userSession);
        });

        return true;
    }


}
