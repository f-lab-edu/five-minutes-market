package kr.fiveminutesmarket.order.domain;

public class Amount {

    private final Integer amount;

    public Amount(Integer amount) {
        validate(amount);

        this.amount = amount;
    }

    private void validate(Integer amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("수량은 0보다 작을수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
