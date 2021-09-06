package kr.fiveminutesmarket.order.dto;

import java.util.List;

public class OrdersRequestDto {

    private String itemName;

    private Integer quantity;

    private Integer totalPrice;

    private String address;

    private String message;

    private List<OrderProductRequestDto> productList;

    public OrdersRequestDto() {
    }

    public OrdersRequestDto(String itemName, Integer quantity, Integer totalPrice, String address, String message, List<OrderProductRequestDto> productList) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.address = address;
        this.message = message;
        this.productList = productList;
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
}
