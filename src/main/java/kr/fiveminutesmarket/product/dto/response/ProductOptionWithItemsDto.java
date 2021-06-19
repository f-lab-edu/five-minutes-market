package kr.fiveminutesmarket.product.dto.response;

import java.util.List;

public class ProductOptionWithItemsDto {

    private Long productOptionId;

    private Boolean optional;

    private List<ProductOptionItemResponseDto> productOptionItemList;

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

    public List<ProductOptionItemResponseDto> getProductOptionItemList() {
        return productOptionItemList;
    }

    public void setProductOptionItemList(List<ProductOptionItemResponseDto> productOptionItemList) {
        this.productOptionItemList = productOptionItemList;
    }
}
