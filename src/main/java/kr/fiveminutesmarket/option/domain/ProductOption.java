package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.ProductOptionResponse;

public class ProductOption {

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

    public void setIsOptional(Boolean optional) {
        isOptional = optional;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
