package kr.fiveminutesmarket.order.domain;

public class OrderProduct {

    private Long orderProductId;

    private String productName;

    private Integer amount;

    private Integer price;

    private Long orderId;

    public OrderProduct() {
    }

    public OrderProduct(Long orderProductId, String productName, Integer amount, Integer price, Long orderId) {
        this.orderProductId = orderProductId;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.orderId = orderId;
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

    public Long getOrderId() {
        return orderId;
    }
}
