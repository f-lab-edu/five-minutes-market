package kr.fiveminutesmarket.category.dto.request;

import kr.fiveminutesmarket.category.domain.MainCategory;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class MainCategoryReqeust {

    @NotBlank
    @Length(max = 50)
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
