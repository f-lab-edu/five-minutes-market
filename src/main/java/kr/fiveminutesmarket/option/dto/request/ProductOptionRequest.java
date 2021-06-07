package kr.fiveminutesmarket.option.dto.request;

import kr.fiveminutesmarket.option.domain.ProductOption;

import javax.validation.constraints.NotNull;

public class ProductOptionRequest {

    @NotNull
    private Boolean optional;

    private Long productId;

    public Boolean isOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductOption toEntity() {
        return new ProductOption(optional, productId);
    }
}
