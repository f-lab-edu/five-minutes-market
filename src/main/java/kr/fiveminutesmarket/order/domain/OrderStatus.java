package kr.fiveminutesmarket.order.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum OrderStatus {
    CANCELED("주문취소"),
    ARRIVAL("배송완료"),
    DELIVERY("배송중",
            OrderStatus.ARRIVAL.status),
    ARRANGED("배송준비",
            OrderStatus.DELIVERY.status),
    PAYMENT_COMPLETED("결제완료",
            OrderStatus.ARRANGED.status,
            OrderStatus.CANCELED.status),
    PAYMENT_WAITING("결제대기",
            OrderStatus.PAYMENT_COMPLETED.status,
            OrderStatus.CANCELED.status),
    COMPLETED("주문완료",
            OrderStatus.PAYMENT_WAITING.status,
            OrderStatus.CANCELED.status);

    private final String status;
    private final Set<String> covertToList;

    OrderStatus(String status, String... covertToStatus) {
        this.status = status;
        this.covertToList = new HashSet<>(Arrays.asList(covertToStatus));
    }

    public String getStatus() {
        return status;
    }

    public void validate(String convertedTo) {
        if(!covertToList.contains(convertedTo))
            throw new OrderStatusNotPossibleConvertException(status, convertedTo);
    }

    public static class OrderStatusNotPossibleConvertException extends RuntimeException {
        OrderStatusNotPossibleConvertException(String fromStatus, String toStatus) {
            super("\"" + fromStatus + "\" 상태에서 \"" + toStatus + "\" 상태로 전환하는 것은 불가능합니다.");
        }
    }
}
