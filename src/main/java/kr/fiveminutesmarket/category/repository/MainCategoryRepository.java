package kr.fiveminutesmarket.category.repository;

import kr.fiveminutesmarket.category.domain.MainCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MainCategoryRepository {

    int insert(@Param("mainCategory") MainCategory mainCategory);

    List<MainCategory> findAll();

    MainCategory findById(@Param("mainCategoryId") Long mainCategoryId);

    int findByName(@Param("mainCategoryName") String mainCategoryName);

    int updateMainCategory(@Param("mainCategoryId") Long mainCategoryId,
                           @Param("mainCategory") MainCategory mainCategory);

    void deleteById(@Param("mainCategoryId") Long mainCategoryId);
}
