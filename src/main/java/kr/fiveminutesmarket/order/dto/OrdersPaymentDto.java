package kr.fiveminutesmarket.order.dto;

import java.util.List;
import java.util.UUID;

public class OrdersPaymentDto {

    private UUID ordersId;

    private String itemName;

    private Integer quantity;

    private Integer totalPrice;

    private String address;

    private String message;

    private List<OrderProductRequestDto> productList;

    private Long userId;

    private String tid;

    public OrdersPaymentDto() {
    }

    public OrdersPaymentDto(String itemName,
                            Integer quantity,
                            Integer totalPrice,
                            String address,
                            String message,
                            List<OrderProductRequestDto> productList,
                            Long userId) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.address = address;
        this.message = message;
        this.productList = productList;
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    public List<OrderProductRequestDto> getProductList() {
        return productList;
    }

    public String getTid() {
        return tid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
