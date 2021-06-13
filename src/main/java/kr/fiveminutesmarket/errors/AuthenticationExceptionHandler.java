package kr.fiveminutesmarket.errors;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.common.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto<?> handlerAuthenticationException(AuthenticationException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
