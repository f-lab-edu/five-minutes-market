package kr.fiveminutesmarket.option.error.exception;

public class ProductOptionItemNotFoundException extends RuntimeException{

    public ProductOptionItemNotFoundException(Long id) {
        super("#" + id + " ProductOptionItem Not Found");
    }
}
