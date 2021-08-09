package kr.fiveminutesmarket.order.domain;

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
            throw new IllegalArgumentException(AMOUNT_VALID_EXCEPTION_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
