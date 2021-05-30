package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.request.ProductOptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionItemResponse;

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

    public void updateInfo(ProductOptionItemRequest resource) {
        this.productOptionItemName = resource.getProductOptionItemName();
        this.productOptionItemPrice = resource.getProductOptionItemPrice();
    }

    public ProductOptionItemResponse toResponse() {
        ProductOptionItemResponse response = new ProductOptionItemResponse();
        response.setProductOptionItemId(productOptionItemId);
        response.setProductOptionItemName(productOptionItemName);
        response.setProductOptionItemPrice(productOptionItemPrice);
        response.setProductOptionId(productOptionId);

        return response;
    }
}
