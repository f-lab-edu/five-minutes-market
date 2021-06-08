package kr.fiveminutesmarket.product.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.error.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"kr.fiveminutesmarket.product"})
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handlerNotFoundException(ProductNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handlerNotEqualsException(IllegalArgumentException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
