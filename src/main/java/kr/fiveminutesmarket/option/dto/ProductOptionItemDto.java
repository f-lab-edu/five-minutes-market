package kr.fiveminutesmarket.option.dto;

public class ProductOptionItemDto {

    private Long productOptionItemId;

    private String productOptionItemName;

    private Integer productOptionItemPrice;

    public Long getProductOptionItemId() {
        return productOptionItemId;
    }

    public void setProductOptionItemId(Long productOptionItemId) {
        this.productOptionItemId = productOptionItemId;
    }

    public String getProductOptionItemName() {
        return productOptionItemName;
    }

    public void setProductOptionItemName(String productOptionItemName) {
        this.productOptionItemName = productOptionItemName;
    }

    public Integer getProductOptionItemPrice() {
        return productOptionItemPrice;
    }

    public void setProductOptionItemPrice(Integer productOptionItemPrice) {
        this.productOptionItemPrice = productOptionItemPrice;
    }
}
