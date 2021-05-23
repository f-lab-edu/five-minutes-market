package kr.fiveminutesmarket.category.mapper;

import kr.fiveminutesmarket.category.domain.SubCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubCategoryMapper {

    int insert(@Param("subCategory") SubCategory subCategory);

    List<SubCategory> findAll();

    SubCategory findBySubCategoryId(@Param("subCategoryId") Long subCategoryId);

    int updateSubCategory(@Param("subCategoryId") Long subCategoryId,
                           @Param("subCategory") SubCategory subCategory);
}
