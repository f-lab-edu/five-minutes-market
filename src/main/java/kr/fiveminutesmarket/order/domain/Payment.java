package kr.fiveminutesmarket.order.domain;

public enum Payment {
    CREDIT("신용카드"), CHECK("체크카드");

    private final String channel;

    Payment(String channel) {
        this.channel = channel;
    }

    public String getValue() {
        return channel;
    }
}
