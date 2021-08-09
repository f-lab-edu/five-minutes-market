package kr.fiveminutesmarket.order.domain;

public class Price {

    private final Integer price;

    public Price(Integer price) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 0보다 작을수 없습니다.");
        }

        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
