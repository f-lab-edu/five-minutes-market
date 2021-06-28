package kr.fiveminutesmarket.user.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.user.error.exception.ExpiredPasswordResetKeyException;
import kr.fiveminutesmarket.user.error.exception.UserEmailExistedException;
import kr.fiveminutesmarket.user.error.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleNotFoundException(UserNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(UserEmailExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleEmailExistedException(UserEmailExistedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(ExpiredPasswordResetKeyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> handleFailSendMailException(ExpiredPasswordResetKeyException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

}
