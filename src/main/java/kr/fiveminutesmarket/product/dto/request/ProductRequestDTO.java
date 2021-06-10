package kr.fiveminutesmarket.product.dto.request;

import kr.fiveminutesmarket.product.domain.Product;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class ProductRequestDTO {

    private Long mainCategoryId;

    private Long subCategoryId;

    private String sellerId;

    @Positive(message = "상품 수량은 0보다 큰 정수 값을 입력해주세요.")
    private Integer quantity;

    @NotBlank(message = "상품 이름은 필수 입력 값입니다.")
    @Length(max = 50, message = "상품 이름의 길이는 50자 제한입니다.")
    private String name;

    @Positive(message = "상품 가격은 0보다 큰 정수 값을 입력해주세요.")
    private Integer price;

    @Length(max = 200, message = "상품 썸네일 이미지주소는 200자 제한입니다.")
    private String thumb;

    @Length(max = 1024, message = "상품 상세 내용은 1024자 제한입니다.")
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
