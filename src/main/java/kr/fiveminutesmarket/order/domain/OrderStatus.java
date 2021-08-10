package kr.fiveminutesmarket.order.domain;

public enum OrderStatus {
    COMPLETED("주문완료"), WAITING("배송준비"), DELIVERY("배송중"), CANCELED("주문취소"), ARRIVAL("배송완료");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}
