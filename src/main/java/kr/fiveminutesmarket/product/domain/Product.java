package kr.fiveminutesmarket.product.domain;

public class Product {

    private Long productId;

    private Long mainCategoryId;

    private Long subCategoryId;

    private String sellerId;

    private Integer quantity;

    private String name;

    private Integer price;

    private String thumb;

    private String detail;

    public Product(Long mainCategoryId, Long subCategoryId, String sellerId, Integer quantity, String name, Integer price, String thumb, String detail) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.thumb = thumb;
        this.detail = detail;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getThumb() {
        return thumb;
    }

    public String getDetail() {
        return detail;
    }
}
