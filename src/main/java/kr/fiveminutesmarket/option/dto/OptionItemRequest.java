package kr.fiveminutesmarket.option.dto;

import kr.fiveminutesmarket.option.domain.OptionItem;

public class OptionItemRequest {

    private Long optionItemId;

    private String optionItemName;

    private Integer optionItemPrice;

    private Long optionId;

    public Long getOptionItemId() {
        return optionItemId;
    }

    public void setOptionItemId(Long optionItemId) {
        this.optionItemId = optionItemId;
    }

    public String getOptionItemName() {
        return optionItemName;
    }

    public void setOptionItemName(String optionItemName) {
        this.optionItemName = optionItemName;
    }

    public Integer getOptionItemPrice() {
        return optionItemPrice;
    }

    public void setOptionItemPrice(Integer optionItemPrice) {
        this.optionItemPrice = optionItemPrice;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public OptionItem toEntity() {
        OptionItem entity = new OptionItem();
        entity.setOptionItemName(optionItemName);
        entity.setOptionItemPrice(optionItemPrice);
        entity.setOptionId(optionId);

        return entity;
    }
}
