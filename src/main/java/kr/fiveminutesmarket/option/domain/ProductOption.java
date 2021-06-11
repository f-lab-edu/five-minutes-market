package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.request.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionResponse;

public class ProductOption {

    private Long productOptionId;

    private Boolean optional;

    private Long productId;

    public ProductOption() {
    }

    public ProductOption(Boolean isOptional, Long productId) {
        this.optional = isOptional;
        this.productId = productId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public Boolean isOptional() {
        return optional;
    }

    public Long getProductId() {
        return productId;
    }

    public void updateInfo(ProductOptionRequest resource) {
        this.optional = resource.isOptional();
        this.productId = resource.getProductId();
    }

    public ProductOptionResponse toResponse() {
        ProductOptionResponse response = new ProductOptionResponse();
        response.setProductOptionId(productOptionId);
        response.setOptional(optional);
        response.setProductId(productId);

        return response;
    }

}
