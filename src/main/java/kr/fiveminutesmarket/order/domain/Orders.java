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
                  OrderStatus orderStatus,
                  String message,
                  LocalDateTime createdDate,
                  LocalDateTime updatedDate,
                  Long userId,
                  List<OrderProduct> orderProducts) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.payment = payment;
        this.orderStatus = orderStatus;
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
}
