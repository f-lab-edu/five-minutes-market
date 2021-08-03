package kr.fiveminutesmarket.order.dto;

public class OrderProductResponseDto {

    private Long orderProductId;

    private String productName;

    private Integer amount;

    private Integer price;

    public OrderProductResponseDto() {
    }

    public OrderProductResponseDto(Long orderProductId, String productName, Integer amount, Integer price) {
        this.orderProductId = orderProductId;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getPrice() {
        return price;
    }
}
