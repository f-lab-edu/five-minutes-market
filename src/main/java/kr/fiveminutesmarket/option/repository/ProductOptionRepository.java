package kr.fiveminutesmarket.option.repository;

import kr.fiveminutesmarket.option.domain.ProductOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOptionRepository {

    int insert(@Param("productOption") ProductOption productOption);

    List<ProductOption> findAll();

    ProductOption findById(@Param("productOptionId") Long productOptionId);

    int updateProductOption(@Param("productOptionId") Long productOptionId,
                            @Param("productOption") ProductOption productOption);

    void deleteById(@Param("productOptionId") Long productOptionId);
}
