package kr.fiveminutesmarket.category.dto.request;

import kr.fiveminutesmarket.category.domain.SubCategory;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SubCategoryReqeust {

    @NotBlank(message = "카테고리 이름은 필수 입력 값입니다.")
    @Length(max = 50, message = "카테고리 이름의 길이는 50자 제한입니다.")
    @Pattern(regexp = "[\\w ㄱ-ㅎㅏ-ㅣ가-힣]+$", message = "특수문자는 입력할 수 없습니다.")
    private String subCategoryName;

    private Long mainCategoryId;

    public SubCategoryReqeust() {
    }

    public SubCategoryReqeust(String subCategoryName, Long mainCategoryId) {
        this.subCategoryName = subCategoryName;
        this.mainCategoryId = mainCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public SubCategory toEntity() {
        return new SubCategory(subCategoryName, mainCategoryId);
    }
}
