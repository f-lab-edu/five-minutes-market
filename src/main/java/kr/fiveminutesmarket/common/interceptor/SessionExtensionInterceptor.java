package kr.fiveminutesmarket.common.interceptor;

import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionExtensionInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final int BEARER_PREFIX_COUNT = 7;

    private final RedisAuthUtils redisAuthUtils;

    public SessionExtensionInterceptor(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = extractToken(request);

        if (StringUtils.hasText(token)) {
            UserSessionDto userSession = redisAuthUtils.getSession(token);
            // 세션 만료시간 갱신
            redisAuthUtils.renewSessionExpire(userSession, token);
            // interceptor 전달을 위한 처리
            request.setAttribute("authSession", userSession);
        }

        return true;
    }

    // Bearer Prefix를 가진 토큰 추출
    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(BEARER_PREFIX_COUNT);
        }

        return null;
    }
}
