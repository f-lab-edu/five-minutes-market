package kr.fiveminutesmarket.errors;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        FieldError fieldError = bindingResult.getFieldError();
        String message = "";

        if(fieldError != null)
            message = fieldError.getDefaultMessage();

        return new ResponseDto<>(-1, message);
    }

}
