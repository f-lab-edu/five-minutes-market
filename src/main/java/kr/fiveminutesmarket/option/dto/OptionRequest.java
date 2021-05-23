package kr.fiveminutesmarket.option.dto;

import kr.fiveminutesmarket.option.domain.Option;

public class OptionRequest {

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

    public void setIsOptional(Boolean isOptional) {
        this.isOptional = isOptional;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Option toEntity() {
        Option entity = new Option();
        entity.setIsOptional(isOptional);
        entity.setProductId(productId);

        return entity;
    }
}
