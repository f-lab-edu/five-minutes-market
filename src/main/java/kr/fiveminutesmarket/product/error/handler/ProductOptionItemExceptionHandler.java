package kr.fiveminutesmarket.product.error.handler;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.error.exception.ParentProductOptionNotExistedException;
import kr.fiveminutesmarket.product.error.exception.ProductOptionItemNameDuplicatedException;
import kr.fiveminutesmarket.product.error.exception.ProductOptionItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductOptionItemExceptionHandler {

    @ExceptionHandler(ProductOptionItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto<?> handleNotFoundException(ProductOptionItemNotFoundException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(ProductOptionItemNameDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleNameDuplicatedException(ProductOptionItemNameDuplicatedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }

    @ExceptionHandler(ParentProductOptionNotExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleNotExistedParentCategoryException(ParentProductOptionNotExistedException exception) {
        return new ResponseDto<>(-1, exception.getMessage());
    }
}
