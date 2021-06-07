package kr.fiveminutesmarket.category.error.handler;

import kr.fiveminutesmarket.category.error.exception.ParentCategoryNotExistedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNotFoundException;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SubCategoryExceptionHandler {

    @ExceptionHandler(SubCategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleNotFoundException(SubCategoryNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(SubCategoryNameDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleNameDuplicatedException(SubCategoryNameDuplicatedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(ParentCategoryNotExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleNotExistedParentCategoryException(ParentCategoryNotExistedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
