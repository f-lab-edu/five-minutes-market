package kr.fiveminutesmarket.product.error.handler;

import kr.fiveminutesmarket.product.error.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"kr.fiveminutesmarket.product"})
public class ProductExceptionHandler {

    private class ExceptionResponseDTO {
        String message;

        public ExceptionResponseDTO(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handlerNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(new ExceptionResponseDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDTO> handlerNotEqualsException(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ExceptionResponseDTO(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
