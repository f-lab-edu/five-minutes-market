package kr.fiveminutesmarket.category.dto.request;

import kr.fiveminutesmarket.category.domain.SubCategory;

public class SubCategoryReqeust {

    private String subCategoryName;

    private Long mainCategoryId;

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public SubCategory toEntity() {
        return new SubCategory(subCategoryName, mainCategoryId);
    }
}
