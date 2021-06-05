package kr.fiveminutesmarket.category.error.handler;

import kr.fiveminutesmarket.category.error.exception.MainCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNotFoundException;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainCategoryExceptionHandler {

    @ExceptionHandler(MainCategoryNotFoundException.class)
    public ResponseEntity<ResponseDto> handleNotFoundException(MainCategoryNotFoundException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MainCategoryNameDuplicatedException.class)
    public ResponseEntity<ResponseDto> handleNameDuplicatedException(MainCategoryNameDuplicatedException exception) {
        return new ResponseEntity<>(new ResponseDto(-1, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
