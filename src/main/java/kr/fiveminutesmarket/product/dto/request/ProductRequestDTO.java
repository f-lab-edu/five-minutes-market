package kr.fiveminutesmarket.product.dto.request;

import kr.fiveminutesmarket.product.domain.Product;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductRequestDTO {

    private Long mainCategoryId;

    private Long subCategoryId;

    private String sellerId;

    @Positive
    private Integer quantity;

    @NotBlank
    @Length(max = 50)
    private String name;

    @Positive
    private Integer price;

    @Length(max = 200)
    private String thumb;

    @Length(max = 1024)
    private String detail;

    public Product toEntity() {
        return new Product(mainCategoryId, subCategoryId, sellerId, quantity, name, price, thumb, detail);
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Long mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
