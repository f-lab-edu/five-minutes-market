package kr.fiveminutesmarket.order.domain;

import kr.fiveminutesmarket.order.exception.errors.PriceValidException;

public class Price {

    private static final int MINIMUM_PRICE = 0;
    private static final String PRICE_VALID_EXCEPTION_MESSAGE = "가격은 " + MINIMUM_PRICE + "보다 작을수 없습니다.";

    private final Integer price;

    public Price(Integer price) {
        validate(price);

        this.price = price;
    }

    private void validate(Integer price) {
        if (price < MINIMUM_PRICE) {
            throw new PriceValidException(PRICE_VALID_EXCEPTION_MESSAGE);
        }
    }

    public Integer getPrice() {
        return price;
    }
}
