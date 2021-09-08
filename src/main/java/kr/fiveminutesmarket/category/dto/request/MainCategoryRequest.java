package kr.fiveminutesmarket.category.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MainCategoryRequest {

    @NotBlank(message = "카테고리 이름은 필수 입력 값입니다.")
    @Length(max = 50, message = "카테고리 이름의 길이는 50자 제한입니다.")
    @Pattern(regexp = "[\\w ㄱ-ㅎㅏ-ㅣ가-힣]+$", message = "특수문자는 입력할 수 없습니다.")
    private String mainCategoryName;

    public MainCategoryRequest() {
    }

    public MainCategoryRequest(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

}
