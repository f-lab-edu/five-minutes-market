package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.OptionItemRequest;
import kr.fiveminutesmarket.option.dto.OptionItemResponse;

public class OptionItem {

    private Long optionItemId;

    private String optionItemName;

    private Integer optionItemPrice;

    private Long productOptionId;

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

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public void updateInfo(OptionItemRequest resource) {
        this.optionItemName = resource.getOptionItemName();
        this.optionItemPrice = resource.getOptionItemPrice();
        this.productOptionId = resource.getProductOptionId();
    }

    public OptionItemResponse toResponse() {
        OptionItemResponse response = new OptionItemResponse();
        response.setOptionItemId(optionItemId);
        response.setOptionItemName(optionItemName);
        response.setOptionItemPrice(optionItemPrice);
        response.setProductOptionId(productOptionId);

        return response;
    }
}
