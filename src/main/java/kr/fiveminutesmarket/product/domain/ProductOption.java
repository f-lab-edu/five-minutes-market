package kr.fiveminutesmarket.product.domain;

import kr.fiveminutesmarket.product.dto.request.ProductOptionRequestDto;
import kr.fiveminutesmarket.product.dto.response.ProductOptionResponseDto;
import kr.fiveminutesmarket.product.dto.response.ProductOptionWithItemsDto;

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

    public void updateInfo(ProductOptionRequestDto resource) {
        this.optional = resource.isOptional();
        this.productId = resource.getProductId();
    }

    public ProductOptionResponseDto toResponse() {
        ProductOptionResponseDto response = new ProductOptionResponseDto();
        response.setProductOptionId(productOptionId);
        response.setOptional(optional);

        return response;
    }

}
