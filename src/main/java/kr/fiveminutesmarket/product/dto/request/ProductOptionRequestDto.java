package kr.fiveminutesmarket.product.dto.request;

import kr.fiveminutesmarket.product.domain.ProductOption;

import javax.validation.constraints.NotNull;

public class ProductOptionRequestDto {

    @NotNull(message = "optional: true 혹은 false 로 입력해주세요.")
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
