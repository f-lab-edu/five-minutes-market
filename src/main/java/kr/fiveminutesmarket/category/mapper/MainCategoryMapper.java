package kr.fiveminutesmarket.category.mapper;

import kr.fiveminutesmarket.category.domain.MainCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainCategoryMapper {

    int insert(@Param("mainCategory") MainCategory mainCategory);

    List<MainCategory> findAll();

    MainCategory findByMainCategoryId(@Param("mainCategoryId") Long mainCategoryId);

    int updateMainCategory(@Param("mainCategoryId") Long mainCategoryId,
                           @Param("mainCategory") MainCategory mainCategory);
}
