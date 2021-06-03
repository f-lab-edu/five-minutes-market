package kr.fiveminutesmarket.category.error.exception;

public class ParentCategoryNotExistedException extends RuntimeException{
    public ParentCategoryNotExistedException(Long mainCategoryId) {
        super("SubCategory's MainCategory #" + mainCategoryId + " Is Not Existed");
    }
}
