package kr.fiveminutesmarket.category.error.exception;

public class SubCategoryNameDuplicatedException extends RuntimeException{
    public SubCategoryNameDuplicatedException(String name) {
        super(name + " SubCategory Duplicated");
    }
}
