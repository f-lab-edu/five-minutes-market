package kr.fiveminutesmarket.order.dto;

import kr.fiveminutesmarket.order.domain.OrderStatus;
import kr.fiveminutesmarket.order.domain.OrderStatusUnit;
import kr.fiveminutesmarket.order.domain.Payment;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersByUserResponseDto {

    private Long orderId;

    private Integer totalPrice;

    private String address;

    private Payment payment;

    private OrderStatus orderStatus;

    private String message;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Long userId;

    private List<OrderProductResponseDto> orderProducts;

    public OrdersByUserResponseDto() {
    }

    public OrdersByUserResponseDto(Long orderId,
                                   Integer totalPrice,
                                   String address,
                                   Payment payment,
                                   OrderStatusUnit orderStatusUnit,
                                   String message,
                                   LocalDateTime createdDate,
                                   LocalDateTime updatedDate,
                                   Long userId,
                                   List<OrderProductResponseDto> orderProducts) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.payment = payment;
        this.orderStatus = orderStatusUnit.getStatus();
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

    public List<OrderProductResponseDto> getOrderProducts() {
        return orderProducts;
    }
}
