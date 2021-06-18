package kr.fiveminutesmarket.product.dto.request;

import kr.fiveminutesmarket.product.domain.ProductOptionItem;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class ProductOptionItemRequestDto {

    @NotBlank(message = "상품옵션 아이템 이름은 필수 입력 값입니다.")
    @Length(max = 50, message = "상품옵션 아이템 이름의 길이는 50자 제한입니다.")
    @Pattern(regexp = "[\\w ㄱ-ㅎㅏ-ㅣ가-힣]+$", message = "특수문자는 입력할 수 없습니다.")
    private String productOptionItemName;

    @Positive(message = "상품옵션 아이템 가격은 양의 정수로 입력해주세요.")
    private Integer productOptionItemPrice;

    private Long productOptionId;

    public String getProductOptionItemName() {
        return productOptionItemName;
    }

    public void setProductOptionItemName(String productOptionItemName) {
        this.productOptionItemName = productOptionItemName;
    }

    public Integer getProductOptionItemPrice() {
        return productOptionItemPrice;
    }

    public void setProductOptionItemPrice(Integer productOptionItemPrice) {
        this.productOptionItemPrice = productOptionItemPrice;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public ProductOptionItem toEntity() {
        return new ProductOptionItem(productOptionItemName, productOptionItemPrice, productOptionId);
    }
}
