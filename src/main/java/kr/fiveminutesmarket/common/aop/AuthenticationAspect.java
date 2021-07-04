package kr.fiveminutesmarket.common.aop;

import kr.fiveminutesmarket.common.annotation.Authentication;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.common.exception.AuthenticationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@Component
public class AuthenticationAspect {

    @Around("@annotation(kr.fiveminutesmarket.common.annotation.Authentication)")
    public Object authenticationCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Authentication auth = method.getAnnotation(Authentication.class);

        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        UserSessionDto userSession = (UserSessionDto)session.getAttribute("user");

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
