package kr.fiveminutesmarket.order.domain;

public class ProductInfo {

    private final String productName;

    private final Amount amount;

    private final Price price;

    public ProductInfo(String productName, Integer amount, Integer price) {
        this.productName = productName;
        this.amount = new Amount(amount);
        this.price = new Price(price);
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
}
