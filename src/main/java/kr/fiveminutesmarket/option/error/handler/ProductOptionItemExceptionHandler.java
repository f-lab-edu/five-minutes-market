package kr.fiveminutesmarket.option.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.option.error.exception.ParentProductOptionNotExistedException;
import kr.fiveminutesmarket.option.error.exception.ProductOptionItemNameDuplicatedException;
import kr.fiveminutesmarket.option.error.exception.ProductOptionItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductOptionItemExceptionHandler {

    @ExceptionHandler(ProductOptionItemNotFoundException.class)
    public ResponseEntity<ResponseDto> handleNotFoundException(ProductOptionItemNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductOptionItemNameDuplicatedException.class)
    public ResponseEntity<ResponseDto> handleNameDuplicatedException(ProductOptionItemNameDuplicatedException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParentProductOptionNotExistedException.class)
    public ResponseEntity<ResponseDto> handleNotExistedParentCategoryException(ParentProductOptionNotExistedException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
