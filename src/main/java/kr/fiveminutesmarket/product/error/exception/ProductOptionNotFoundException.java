package kr.fiveminutesmarket.product.error.exception;

public class ProductOptionNotFoundException extends RuntimeException{

    public ProductOptionNotFoundException(Long id) {
        super("#" + id + " ProductOption Not Found");
    }
}
