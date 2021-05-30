package kr.fiveminutesmarket.category.error.exception;

public class SubCategoryNotFoundException extends RuntimeException{

    public SubCategoryNotFoundException(Long id) {
        super("#" + id + " SubCategory Not Found");
    }
}
