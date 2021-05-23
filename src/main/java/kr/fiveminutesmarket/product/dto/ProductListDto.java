package kr.fiveminutesmarket.product.dto;

import kr.fiveminutesmarket.category.domain.MainCategory;
import kr.fiveminutesmarket.category.domain.SubCategory;
import kr.fiveminutesmarket.option.dto.OptionDto;

import java.util.List;

public class ProductListDto {

    private Long productId;

    private String sellerId;

    private Integer quantity;

    private String name;

    private Integer price;

    private String thumb;

    private String detail;

    private MainCategory mainCategory;

    private SubCategory subCategory;

    private List<OptionDto> options;

    public Long getProductId() {
        return productId;
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

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public List<OptionDto> getOptions() {
        return options;
    }
}
