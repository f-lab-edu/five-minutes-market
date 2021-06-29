package kr.fiveminutesmarket.outbox.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.outbox.error.exception.PayloadConvertFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OutBoxExceptionHandler {
    @ExceptionHandler(PayloadConvertFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handlePayloadConvertFailedException(PayloadConvertFailedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
