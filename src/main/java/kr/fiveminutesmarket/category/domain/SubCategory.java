package kr.fiveminutesmarket.category.domain;

import kr.fiveminutesmarket.category.dto.request.SubCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;

public class SubCategory {

    private Long subCategoryId;

    private String subCategoryName;

    private Long mainCategoryId;

    public SubCategory() {
    }

    public SubCategory(String subCategoryName, Long mainCategoryId) {
        this.subCategoryName = subCategoryName;
        this.mainCategoryId = mainCategoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
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
