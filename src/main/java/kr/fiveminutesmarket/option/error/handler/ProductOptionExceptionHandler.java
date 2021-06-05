package kr.fiveminutesmarket.option.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.option.error.exception.ProductOptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductOptionExceptionHandler {

    @ExceptionHandler(ProductOptionNotFoundException.class)
    public ResponseEntity<ResponseDto> handleNotFoundException(ProductOptionNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.NOT_FOUND);
    }

}
