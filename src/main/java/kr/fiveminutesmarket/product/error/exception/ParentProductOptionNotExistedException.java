package kr.fiveminutesmarket.product.error.exception;

public class ParentProductOptionNotExistedException extends RuntimeException{
    public ParentProductOptionNotExistedException(Long productOptionId) {
        super("ProductOptionItem's ProductOption #" + productOptionId + " Is Not Existed");
    }
}
