package kr.fiveminutesmarket.common.aop;

import kr.fiveminutesmarket.common.annotation.Authentication;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.errors.AuthenticationException;
import kr.fiveminutesmarket.common.exception.errors.TokenNotExistedException;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
@Component
public class AuthenticationAspect {

    private final RedisAuthUtils redisAuthUtils;

    public AuthenticationAspect(RedisAuthUtils redisAuthUtils) {
        this.redisAuthUtils = redisAuthUtils;
    }

    @Around("@annotation(kr.fiveminutesmarket.common.annotation.Authentication)")
    public Object authenticationCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authentication auth = method.getAnnotation(Authentication.class);

        Optional<String> bearerValue = Optional.ofNullable(
                ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getHeader("Authorization"));

        if (bearerValue.isEmpty()) {
            throw new TokenNotExistedException();
        }

        UserSessionDto userSession = redisAuthUtils.getSession(bearerValue.get());

        if (userSession == null) {
            throw new AuthenticationException("로그인이 필요합니다.");
        }

        if (auth.seller()) {
            isSeller(userSession);
        }

        return joinPoint.proceed();
    }

    private void isSeller(UserSessionDto userSession) {
        if (userSession.getSeller() == null ||
                !userSession.getSeller()) {
            throw new AuthenticationException("판매자 권한이 필요합니다.");
        }
    }
}
