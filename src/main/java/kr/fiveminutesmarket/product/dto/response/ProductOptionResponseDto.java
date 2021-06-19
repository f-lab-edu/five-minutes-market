package kr.fiveminutesmarket.product.dto.response;

public class ProductOptionResponseDto {
    private Long productOptionId;

    private Boolean optional;

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }
}
