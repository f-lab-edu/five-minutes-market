package kr.fiveminutesmarket.option.dto;

import java.util.List;

public class ProductOptionDto {

    private Long productOptionId;

    private Boolean optional;

    private Long productId;

    private List<ProductOptionItemDto> productOptionItemList;

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

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

    public List<ProductOptionItemDto> getProductOptionItemList() {
        return productOptionItemList;
    }

    public void setProductOptionItemList(List<ProductOptionItemDto> productOptionItemList) {
        this.productOptionItemList = productOptionItemList;
    }
}
