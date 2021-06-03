package kr.fiveminutesmarket.category.error.exception;

public class MainCategoryNameDuplicatedException extends RuntimeException{

    public MainCategoryNameDuplicatedException(String name) {
        super(name + " MainCategory Duplicated");
    }
}
