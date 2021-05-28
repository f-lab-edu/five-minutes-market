package kr.fiveminutesmarket.option.dto.request;

import kr.fiveminutesmarket.option.domain.ProductOption;

public class ProductOptionRequest {

    private Boolean isOptional;

    private Long productId;

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
        return new ProductOption(isOptional, productId);
    }
}
