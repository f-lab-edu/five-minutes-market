package kr.fiveminutesmarket.category.domain;

import kr.fiveminutesmarket.category.dto.MainCategoryResponse;

public class MainCategory {

    private Long mainCategoryId;

    private String mainCategoryName;

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public MainCategoryResponse toResponse() {
        MainCategoryResponse response = new MainCategoryResponse();
        response.setMainCategoryId(mainCategoryId);
        response.setMainCategoryName(mainCategoryName);

        return response;
    }
}
