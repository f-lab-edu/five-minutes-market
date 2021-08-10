package kr.fiveminutesmarket.order.domain;

public class OrderStatusUnit {
    private OrderStatus orderStatus;

    private OrderStatusUnit() {
    }

    private OrderStatusUnit(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public static OrderStatusUnit initialize() {
        return new OrderStatusUnit(OrderStatus.COMPLETED);
    }

    /**
     * 주문상태 다음단계에 대한 제약조건 설정
     */
    // new OrderStatusUnit 방식이 맞는 것인지(새로운 객체 생성 -> 비효율적이라 생각)
    public void nextStep() {
        switch (orderStatus.getValue()) {
            case "주문완료": orderStatus = OrderStatus.WAITING; break;
            case "배송준비": orderStatus = OrderStatus.DELIVERY; break;
            case "배송중": orderStatus = OrderStatus.ARRIVAL; break;
            default: throw new OrderStatusNextStepLimited();
        }
    }

    public void cancel() {
        if(orderStatus == OrderStatus.ARRIVAL) {
            throw new OrderStatusCouldNotBeCanceledException();
        }
        orderStatus = OrderStatus.CANCELED;
    }

    private static class OrderStatusCouldNotBeCanceledException extends RuntimeException {
        OrderStatusCouldNotBeCanceledException() {
            super("배송완료 상태에서 주문을 취소할 수 없습니다.");
        }
    }

    private static class OrderStatusNextStepLimited extends RuntimeException {
        OrderStatusNextStepLimited() {
            super("해당 단계는 다음 단계가 존재하지 않습니다.");
        }
    }

}
