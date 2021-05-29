package kr.fiveminutesmarket.option.dto.response;

public class ProductOptionItemResponse {

    private Long productOptionItemId;

    private String productOptionItemName;

    private Integer productOptionItemPrice;

    private Long productOptionId;

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

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }
}
