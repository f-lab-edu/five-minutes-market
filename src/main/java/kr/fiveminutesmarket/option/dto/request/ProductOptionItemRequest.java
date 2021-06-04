package kr.fiveminutesmarket.option.dto.request;

import kr.fiveminutesmarket.option.domain.ProductOptionItem;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductOptionItemRequest {

    @NotBlank
    @Length(max = 50)
    private String productOptionItemName;

    @Positive
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
