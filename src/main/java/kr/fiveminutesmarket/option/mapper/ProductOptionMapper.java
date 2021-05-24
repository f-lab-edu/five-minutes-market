package kr.fiveminutesmarket.option.mapper;

import kr.fiveminutesmarket.option.domain.ProductOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOptionMapper {

    int insert(@Param("productOption") ProductOption productOption);

    List<ProductOption> findAll();

    ProductOption findByProductOptionId(@Param("productOptionId") Long productOptionId);

    int updateProductOption(@Param("productOptionId") Long productOptionId,
                            @Param("productOption") ProductOption productOption);

}
