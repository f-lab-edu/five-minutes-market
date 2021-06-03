package kr.fiveminutesmarket.option.error.handler;

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
    public ResponseEntity<?> handleNotFoundException(ProductOptionItemNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductOptionItemNameDuplicatedException.class)
    public ResponseEntity<?> handleNameDuplicatedException(ProductOptionItemNameDuplicatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParentProductOptionNotExistedException.class)
    public ResponseEntity<?> handleNotExistedParentCategoryException(ParentProductOptionNotExistedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
