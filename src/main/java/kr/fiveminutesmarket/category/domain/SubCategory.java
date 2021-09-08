package kr.fiveminutesmarket.category.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name = "main_category_id")
    @JsonIgnore
    private MainCategory mainCategory;

    public SubCategory() {
    }

    public SubCategory(String subCategoryName, MainCategory mainCategory) {
        this.subCategoryName = subCategoryName;
        this.mainCategory = mainCategory;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void updateInfo(String subCategoryName, MainCategory mainCategory) {
        this.subCategoryName = subCategoryName;
        this.mainCategory = mainCategory;
    }
}
