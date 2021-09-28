package kr.fiveminutesmarket.category.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class MainCategoryResponse {

    private Long mainCategoryId;

    private String mainCategoryName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SubCategoryResponse> subCategoryResponses;

    public MainCategoryResponse() {
    }

    public MainCategoryResponse(Long mainCategoryId, String mainCategoryName, List<SubCategoryResponse> subCategoryResponses) {
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryName = mainCategoryName;
        this.subCategoryResponses = subCategoryResponses;
    }

    public Long getMainCategoryId() {
        return mainCategoryId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public List<SubCategoryResponse> getSubCategoryResponses() {
        return subCategoryResponses;
    }
}
