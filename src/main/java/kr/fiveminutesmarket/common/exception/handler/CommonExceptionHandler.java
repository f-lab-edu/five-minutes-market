package kr.fiveminutesmarket.common.exception.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.common.exception.errors.JsonDeserializeFailedException;
import kr.fiveminutesmarket.common.exception.errors.JsonSerializeFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ResponseDto<>(-1, "잘못된 입력값을 기입했습니다.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleNotEqualsException(IllegalArgumentException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(JsonDeserializeFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleJsonDeserializeFailedException(JsonDeserializeFailedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(JsonSerializeFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleJsonSerializeFailedException(JsonSerializeFailedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
