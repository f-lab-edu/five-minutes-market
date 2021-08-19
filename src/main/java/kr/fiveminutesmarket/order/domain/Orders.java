package kr.fiveminutesmarket.order.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Orders {

    private Long orderId;

    private Integer totalPrice;

    private String address;

    private Payment payment;

    private OrderStatus orderStatus;

    private String message;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Long userId;

    private List<OrderProduct> orderProducts;

    public Orders() {
    }

    public Orders(Long orderId,
                  Integer totalPrice,
                  String address,
                  Payment payment,
                  String message,
                  LocalDateTime createdDate,
                  LocalDateTime updatedDate,
                  Long userId,
                  List<OrderProduct> orderProducts) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.payment = payment;
        this.orderStatus = OrderStatus.COMPLETED;
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

    public Payment getPayment() {
        return payment;
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

    public void updateOrderStatus(OrderStatus changeToStatus) {
        validate(orderStatus, changeToStatus);
        orderStatus = changeToStatus;
    }

    /**
     * 현재 주문상태에서 갱신하고자하는 주문상태로 전환 가능한지 타당성 여부 검토
     * @param from 현재 주문상태
     * @param to 갱신하고자 하는 주문상태
     */
    private void validate(OrderStatus from, OrderStatus to) {
        from.validate(to.getStatus());
    }
}
