package kr.fiveminutesmarket.option.dto;

public class OptionResponse {

    private Long optionId;

    private Boolean isOptional;

    private Long productId;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(Boolean optional) {
        isOptional = optional;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
