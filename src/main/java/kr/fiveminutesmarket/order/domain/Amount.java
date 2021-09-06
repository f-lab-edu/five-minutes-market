package kr.fiveminutesmarket.order.domain;

import kr.fiveminutesmarket.order.exception.errors.AmountValidException;

public class Amount {

    private static final int MINIMUM_AMOUNT = 0;
    private static final String AMOUNT_VALID_EXCEPTION_MESSAGE = "수량은 " + MINIMUM_AMOUNT + "보다 작을수 없습니다.";

    private final Integer amount;

    public Amount(Integer amount) {
        validate(amount);

        this.amount = amount;
    }

    private void validate(Integer amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new AmountValidException(AMOUNT_VALID_EXCEPTION_MESSAGE);
        }
    }

    public Integer getAmount() {
        return amount;
    }
}
