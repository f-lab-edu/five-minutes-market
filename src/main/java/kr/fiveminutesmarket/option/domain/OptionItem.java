package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.OptionItemRequest;
import kr.fiveminutesmarket.option.dto.OptionItemResponse;

public class OptionItem {

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

    public void updateInfo(OptionItemRequest resource) {
        this.optionItemName = resource.getOptionItemName();
        this.optionItemPrice = resource.getOptionItemPrice();
        this.optionId = resource.getOptionId();
    }

    public OptionItemResponse toResponse() {
        OptionItemResponse response = new OptionItemResponse();
        response.setOptionItemId(optionItemId);
        response.setOptionItemName(optionItemName);
        response.setOptionItemPrice(optionItemPrice);
        response.setOptionId(optionId);

        return response;
    }
}
