package kr.fiveminutesmarket.order.domain;

public class OrderProduct {

    private Long orderProductId;

    private String productName;

    private Amount amount;

    private Price price;

    private Long orderId;

    public OrderProduct() {
    }

    public OrderProduct(String productName, Integer amount, Integer price, Long orderId) {
        this.productName = productName;
        this.amount = new Amount(amount);
        this.price = new Price(price);
        this.orderId = orderId;
    }

    public OrderProduct(Long orderProductId, String productName, Integer amount, Integer price, Long orderId) {
        this.orderProductId = orderProductId;
        this.productName = productName;
        this.amount = new Amount(amount);
        this.price = new Price(price);
        this.orderId = orderId;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmount() {
        return amount.getAmount();
    }

    public Integer getPrice() {
        return price.getPrice();
    }

    public Long getOrderId() {
        return orderId;
    }
}
