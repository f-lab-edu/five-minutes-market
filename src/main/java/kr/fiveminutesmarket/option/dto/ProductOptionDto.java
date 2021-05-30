package kr.fiveminutesmarket.option.dto;

import java.util.List;

public class ProductOptionDto {

    private Long productOptionId;

    private Boolean isOptional;

    private Long productId;

    private List<ProductOptionItemDto> productOptionItemList;

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Boolean getOptional() {
        return isOptional;
    }

    public void setOptional(Boolean optional) {
        isOptional = optional;
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
