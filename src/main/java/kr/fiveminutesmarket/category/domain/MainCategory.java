package kr.fiveminutesmarket.category.domain;

import kr.fiveminutesmarket.category.dto.request.MainCategoryRequest;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;

public class MainCategory {

    private Long mainCategoryId;

    private String mainCategoryName;

    public MainCategory() {
    }

    public MainCategory(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void updateInfo(MainCategoryRequest resource) {
        this.mainCategoryName = resource.getMainCategoryName();
    }

    public MainCategoryResponse toResponse() {
        MainCategoryResponse response = new MainCategoryResponse();
        response.setMainCategoryId(mainCategoryId);
        response.setMainCategoryName(mainCategoryName);

        return response;
    }
}
