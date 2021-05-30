package kr.fiveminutesmarket.option.error.handler;

import kr.fiveminutesmarket.option.error.exception.ProductOptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductOptionExceptionHandler {

    @ExceptionHandler(ProductOptionNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(ProductOptionNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
