package kr.fiveminutesmarket.product.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.error.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"kr.fiveminutesmarket.product"})
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDto> handlerNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> handlerNotEqualsException(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
