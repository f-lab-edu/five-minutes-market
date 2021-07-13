package kr.fiveminutesmarket.common.exception.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.common.exception.errors.AuthenticationException;
import kr.fiveminutesmarket.common.exception.errors.TokenNotExistedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto<?> handleAuthenticationException(AuthenticationException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(TokenNotExistedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDto<?> handleTokenNotExistedException(TokenNotExistedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
