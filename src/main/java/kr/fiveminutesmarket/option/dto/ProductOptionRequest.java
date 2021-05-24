package kr.fiveminutesmarket.option.dto;

import kr.fiveminutesmarket.option.domain.ProductOption;

public class ProductOptionRequest {

    private Long productOptionId;

    private Boolean isOptional;

    private Long productId;

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(Boolean isOptional) {
        this.isOptional = isOptional;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductOption toEntity() {
        ProductOption entity = new ProductOption();
        entity.setIsOptional(isOptional);
        entity.setProductId(productId);

        return entity;
    }
}
