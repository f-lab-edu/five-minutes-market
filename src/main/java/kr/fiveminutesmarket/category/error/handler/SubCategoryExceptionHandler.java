package kr.fiveminutesmarket.category.error.handler;

import kr.fiveminutesmarket.category.error.exception.ParentCategoryNotExistedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubCategoryExceptionHandler {

    @ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(SubCategoryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubCategoryNameDuplicatedException.class)
    public ResponseEntity<?> handleNameDuplicatedException(SubCategoryNameDuplicatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParentCategoryNotExistedException.class)
    public ResponseEntity<?> handleNotExistedParentCategoryException(ParentCategoryNotExistedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
