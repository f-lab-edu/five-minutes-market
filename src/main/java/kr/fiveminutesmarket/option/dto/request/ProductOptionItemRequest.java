package kr.fiveminutesmarket.option.dto.request;

import kr.fiveminutesmarket.option.domain.ProductOptionItem;

public class ProductOptionItemRequest {

    private String productOptionItemName;

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
