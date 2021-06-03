package kr.fiveminutesmarket.category.error.exception;

public class MainCategoryNotFoundException extends RuntimeException{

    public MainCategoryNotFoundException(Long id) {
        super("#" + id + " MainCategory Not Found");
    }
}
