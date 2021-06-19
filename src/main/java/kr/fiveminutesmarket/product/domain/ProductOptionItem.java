package kr.fiveminutesmarket.product.domain;

import kr.fiveminutesmarket.product.dto.request.ProductOptionItemRequestDto;
import kr.fiveminutesmarket.product.dto.response.ProductOptionItemResponseDto;

public class ProductOptionItem {

    private Long productOptionItemId;

    private String productOptionItemName;

    private Integer productOptionItemPrice;

    private Long productOptionId;

    public ProductOptionItem() {
    }

    public ProductOptionItem(String productOptionItemName, Integer productOptionItemPrice, Long productOptionId) {
        this.productOptionItemName = productOptionItemName;
        this.productOptionItemPrice = productOptionItemPrice;
        this.productOptionId = productOptionId;
    }

    public Long getProductOptionItemId() {
        return productOptionItemId;
    }

    public String getProductOptionItemName() {
        return productOptionItemName;
    }

    public Integer getProductOptionItemPrice() {
        return productOptionItemPrice;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public void updateInfo(ProductOptionItemRequestDto resource) {
        this.productOptionItemName = resource.getProductOptionItemName();
        this.productOptionItemPrice = resource.getProductOptionItemPrice();
    }

    public ProductOptionItemResponseDto toResponse() {
        ProductOptionItemResponseDto response = new ProductOptionItemResponseDto();
        response.setProductOptionItemId(productOptionItemId);
        response.setProductOptionItemName(productOptionItemName);
        response.setProductOptionItemPrice(productOptionItemPrice);

        return response;
    }
}
