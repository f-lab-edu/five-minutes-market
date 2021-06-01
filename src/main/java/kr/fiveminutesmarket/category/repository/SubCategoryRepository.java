package kr.fiveminutesmarket.category.repository;

import kr.fiveminutesmarket.category.domain.SubCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubCategoryRepository {

    int insert(@Param("subCategory") SubCategory subCategory);

    List<SubCategory> findAll();

    SubCategory findById(@Param("subCategoryId") Long subCategoryId);

    int countByName(@Param("subCategoryName") String subCategoryName);

    int updateSubCategory(@Param("subCategoryId") Long subCategoryId,
                           @Param("subCategory") SubCategory subCategory);

    void deleteById(@Param("subCategoryId") Long subCategoryId);

    void deleteByMainCategoryId(@Param("mainCategoryId") Long mainCategoryId);
}
