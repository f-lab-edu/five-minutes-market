package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.OptionRequest;
import kr.fiveminutesmarket.option.dto.OptionResponse;

public class Option {

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

    public void updateInfo(OptionRequest resource) {
        this.isOptional = resource.getIsOptional();
        this.productId = resource.getProductId();
    }

    public OptionResponse toResponse() {
        OptionResponse response = new OptionResponse();
        response.setOptionId(optionId);
        response.setIsOptional(isOptional);
        response.setProductId(productId);

        return response;
    }

}
