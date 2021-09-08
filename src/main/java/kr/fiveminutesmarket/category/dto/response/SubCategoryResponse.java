package kr.fiveminutesmarket.category.dto.response;

public class SubCategoryResponse {

    private Long subCategoryId;

    private String subCategoryName;

    public SubCategoryResponse() {
    }

    public SubCategoryResponse(Long subCategoryId, String subCategoryName) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

}
