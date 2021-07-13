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

    private final RedisAuthUtils redisUtils;

    public SessionExtensionInterceptor(RedisAuthUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<String> bearerValue = Optional.ofNullable(request.getHeader("Authorization"));

        // bearerValue 존재하지 않을 때 세션 연장 로직 패스
        bearerValue.ifPresent(bearer -> {
            UserSessionDto sessionDto = redisUtils.getSession(bearer);
            // 세션 만료시간 갱신
            redisUtils.renewSessionExpire(sessionDto, bearer);
        });

        return true;
    }
}
