package kr.fiveminutesmarket.option.dto.request;

import kr.fiveminutesmarket.option.domain.OptionItem;

public class OptionItemRequest {

    private String optionItemName;

    private Integer optionItemPrice;

    private Long productOptionId;

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

    public OptionItem toEntity() {
        return new OptionItem(optionItemName, optionItemPrice, productOptionId);
    }
}
