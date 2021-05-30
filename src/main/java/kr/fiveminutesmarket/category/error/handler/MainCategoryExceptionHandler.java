package kr.fiveminutesmarket.category.error.handler;

import kr.fiveminutesmarket.category.error.exception.MainCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainCategoryExceptionHandler {

    @ExceptionHandler(MainCategoryNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(MainCategoryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MainCategoryNameDuplicatedException.class)
    public ResponseEntity<?> handleNameDuplicatedException(MainCategoryNameDuplicatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
