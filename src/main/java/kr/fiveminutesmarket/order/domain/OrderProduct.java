package kr.fiveminutesmarket.order.domain;

public class OrderProduct {

    private Long orderProductId;

    private ProductInfo productInfo;

    private Long orderId;

    public OrderProduct() {
    }

    public OrderProduct(Long orderProductId, String productName, Integer amount, Integer price, Long orderId) {
        this.orderProductId = orderProductId;
        this.productInfo = new ProductInfo(productName, amount, price);
        this.orderId = orderId;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public String getProductName() {
        return productInfo.getProductName();
    }

    public Integer getAmount() {
        return productInfo.getAmount();
    }

    public Integer getPrice() {
        return productInfo.getPrice();
    }

    public Long getOrderId() {
        return orderId;
    }
}
