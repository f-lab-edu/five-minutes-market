package kr.fiveminutesmarket.category.error.handler;

import kr.fiveminutesmarket.category.error.exception.MainCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNotFoundException;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainCategoryExceptionHandler {

    @ExceptionHandler(MainCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleNotFoundException(MainCategoryNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(MainCategoryNameDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleNameDuplicatedException(MainCategoryNameDuplicatedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
