package kr.fiveminutesmarket.product.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.error.exception.ProductOptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductOptionExceptionHandler {

    @ExceptionHandler(ProductOptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleNotFoundException(ProductOptionNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

}
