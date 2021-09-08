package kr.fiveminutesmarket.category.domain;

import kr.fiveminutesmarket.category.dto.request.MainCategoryRequest;

import javax.persistence.*;
import java.util.List;

@Entity
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainCategoryId;

    private String mainCategoryName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE ,mappedBy = "mainCategory")
    private List<SubCategory> subCategoryList;

    public MainCategory() {
    }

    public MainCategory(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void updateInfo(MainCategoryRequest resource) {
        this.mainCategoryName = resource.getMainCategoryName();
    }

}
