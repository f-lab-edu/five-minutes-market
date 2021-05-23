package kr.fiveminutesmarket.category.dto;

import kr.fiveminutesmarket.category.domain.MainCategory;

public class MainCategoryReqeust {

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

    public MainCategory toEntity() {
        MainCategory entity = new MainCategory();
        entity.setMainCategoryName(mainCategoryName);

        return entity;
    }

}
