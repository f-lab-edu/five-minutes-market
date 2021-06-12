package kr.fiveminutesmarket.user.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.user.error.exception.RoleTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RoleTypeExceptionHandler {

    @ExceptionHandler(RoleTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleRoleTypeNotFoundException(RoleTypeNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
