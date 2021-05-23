package kr.fiveminutesmarket.category.domain;

import kr.fiveminutesmarket.category.dto.SubCategoryReqeust;
import kr.fiveminutesmarket.category.dto.SubCategoryResponse;

public class SubCategory {

    private Long subCategoryId;

    private String subCategoryName;

    private Long mainCategoryId;

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

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

    public SubCategoryResponse toResponse() {
        SubCategoryResponse response = new SubCategoryResponse();
        response.setSubCategoryId(subCategoryId);
        response.setSubCategoryName(subCategoryName);
        response.setMainCategoryId(mainCategoryId);

        return response;
    }

    public void updateInfo(SubCategoryReqeust resource) {
        this.subCategoryName = resource.getSubCategoryName();
        this.mainCategoryId = resource.getMainCategoryId();
    }
}
