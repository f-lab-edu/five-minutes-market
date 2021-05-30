package kr.fiveminutesmarket.category.dto.request;

import kr.fiveminutesmarket.category.domain.MainCategory;

public class MainCategoryReqeust {

    private String mainCategoryName;

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public MainCategory toEntity() {
        return new MainCategory(mainCategoryName);
    }

}
