package kr.fiveminutesmarket.option.dto;

import java.util.List;

public class OptionDto {

    private Long optionId;

    private Boolean isOptional;

    private Long productId;

    private List<OptionItemDto> optionItemList;

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
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

    public List<OptionItemDto> getOptionItemList() {
        return optionItemList;
    }

    public void setOptionItemList(List<OptionItemDto> optionItemList) {
        this.optionItemList = optionItemList;
    }
}
