package kr.fiveminutesmarket.order.exception.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.order.exception.errors.InvalidPaymentMethodException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(InvalidPaymentMethodException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleMethodArgumentNotValid(InvalidPaymentMethodException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
