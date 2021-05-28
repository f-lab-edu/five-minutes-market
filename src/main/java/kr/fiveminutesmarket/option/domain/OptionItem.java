package kr.fiveminutesmarket.option.domain;

import kr.fiveminutesmarket.option.dto.request.OptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.OptionItemResponse;

public class OptionItem {

    private Long optionItemId;

    private String optionItemName;

    private Integer optionItemPrice;

    private Long productOptionId;

    public OptionItem() {
    }

    public OptionItem(String optionItemName, Integer optionItemPrice, Long productOptionId) {
        this.optionItemName = optionItemName;
        this.optionItemPrice = optionItemPrice;
        this.productOptionId = productOptionId;
    }

    public Long getOptionItemId() {
        return optionItemId;
    }

    public String getOptionItemName() {
        return optionItemName;
    }

    public Integer getOptionItemPrice() {
        return optionItemPrice;
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
