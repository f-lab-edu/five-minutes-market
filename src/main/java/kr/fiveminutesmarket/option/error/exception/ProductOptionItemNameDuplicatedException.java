package kr.fiveminutesmarket.option.error.exception;

public class ProductOptionItemNameDuplicatedException extends RuntimeException{

    public ProductOptionItemNameDuplicatedException(String name) {
        super(name + " ProductOptionItem Duplicated");
    }
}
