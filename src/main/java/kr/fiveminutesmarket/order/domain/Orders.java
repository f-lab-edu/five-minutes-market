package kr.fiveminutesmarket.order.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Orders {

    private Long orderId;

    private Integer totalPrice;

    private String address;

    private OrderStatus orderStatus;

    private String message;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Long userId;

    private List<OrderProduct> orderProducts;

    public Orders() {
    }

    public Orders(Integer totalPrice,
                  String address,
                  String message,
                  LocalDateTime createdDate,
                  Long userId) {
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderStatus = OrderStatus.PAYMENT_WAITING;
        this.message = message;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public Orders(Integer totalPrice,
                  String address,
                  String message,
                  LocalDateTime createdDate,
                  LocalDateTime updatedDate,
                  Long userId,
                  List<OrderProduct> orderProducts) {
        this.totalPrice = totalPrice;
        this.address = address;
        this.orderStatus = OrderStatus.PAYMENT_WAITING;
        this.message = message;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.userId = userId;
        this.orderProducts = orderProducts;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void canceled() {
        OrderStatus canceledStatus = OrderStatus.CANCELED;

        validateConverting(orderStatus, canceledStatus);
        orderStatus = canceledStatus;
    }

    public void arrivalDelivery() {
        OrderStatus arrivalStatus = OrderStatus.ARRIVAL;

        validateConverting(orderStatus, arrivalStatus);
        orderStatus = arrivalStatus;
    }

    public void onDelivery() {
        OrderStatus onDeliveryStatus = OrderStatus.DELIVERY;

        validateConverting(orderStatus, onDeliveryStatus);
        orderStatus = onDeliveryStatus;
    }

    public void productArranged() {
        OrderStatus arrangedStatus = OrderStatus.ARRANGED;

        validateConverting(orderStatus, arrangedStatus);
        orderStatus = arrangedStatus;
    }

    public void paymentCompleted() {
        OrderStatus paymentCompletedStatus = OrderStatus.PAYMENT_COMPLETED;

        validateConverting(orderStatus, paymentCompletedStatus);
        orderStatus = paymentCompletedStatus;
    }

    public void paymentWaiting() {
        OrderStatus paymentWaiting = OrderStatus.PAYMENT_WAITING;

        validateConverting(orderStatus, paymentWaiting);
        orderStatus = paymentWaiting;
    }

    /**
     * 현재 주문상태에서 갱신하고자하는 주문상태로 전환 가능한지 타당성 여부 검토
     * @param from 현재 주문상태
     * @param to 갱신하고자 하는 주문상태
     */
    private void validateConverting(OrderStatus from, OrderStatus to) {
        Set<String> convertToList = from.getCovertToList();
        String convertingTargetStatus = to.getStatus();

        if (!convertToList.contains(convertingTargetStatus)) {
            throw new OrderStatus.OrderStatusNotPossibleConvertException(from.getStatus(), to.getStatus());
        }
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", totalPrice=" + totalPrice +
                ", address='" + address + '\'' +
                ", orderStatus=" + orderStatus +
                ", message='" + message + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", userId=" + userId +
                '}';
    }
}
