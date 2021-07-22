package kr.fiveminutesmarket.common.interceptor;

import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class SessionExtensionInterceptor implements HandlerInterceptor {

    private final RedisAuthUtils redisAuthUtils;

    public SessionExtensionInterceptor(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<String> bearerValue = Optional.ofNullable(request.getHeader("Authorization"));

        // bearerValue 존재하지 않을 때 세션 연장 로직 패스
        bearerValue.ifPresent(bearer -> {
            UserSessionDto sessionDto = redisAuthUtils.getSession(bearer.substring(7));
            // 세션 만료시간 갱신
            redisAuthUtils.renewSessionExpire(sessionDto, bearer.substring(7));
        });

        return true;
    }
}
