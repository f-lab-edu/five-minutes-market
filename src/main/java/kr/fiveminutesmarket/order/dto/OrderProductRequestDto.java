package kr.fiveminutesmarket.order.dto;

public class OrderProductRequestDto {

    private String productName;

    private Integer amount;

    private Integer price;

    public OrderProductRequestDto() {
    }

    public OrderProductRequestDto(String productName, Integer amount, Integer price) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
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
