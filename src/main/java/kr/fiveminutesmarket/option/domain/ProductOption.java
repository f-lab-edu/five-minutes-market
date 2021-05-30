package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.request.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionResponse;

public class ProductOption {

    private Long productOptionId;

    private Boolean isOptional;

    private Long productId;

    public ProductOption() {
    }

    public ProductOption(Boolean isOptional, Long productId) {
        this.isOptional = isOptional;
        this.productId = productId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public Long getProductId() {
        return productId;
    }

    public void updateInfo(ProductOptionRequest resource) {
        this.isOptional = resource.getIsOptional();
        this.productId = resource.getProductId();
    }

    public ProductOptionResponse toResponse() {
        ProductOptionResponse response = new ProductOptionResponse();
        response.setProductOptionId(productOptionId);
        response.setIsOptional(isOptional);
        response.setProductId(productId);

        return response;
    }

}
