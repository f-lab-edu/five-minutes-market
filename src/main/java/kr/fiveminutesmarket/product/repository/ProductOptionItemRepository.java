package kr.fiveminutesmarket.product.repository;

import kr.fiveminutesmarket.product.domain.ProductOptionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductOptionItemRepository {

    int insert(@Param("productOptionItem") ProductOptionItem productOptionItem);

    List<ProductOptionItem> findAll();

    ProductOptionItem findById(@Param("productOptionItemId") Long productOptionItemId);

    int countByName(@Param("productOptionItemName") String productOptionItemName);

    int update(@Param("productOptionItemId") Long productOptionItemId,
                     @Param("productOptionItem") ProductOptionItem productOptionItem);

    void deleteById(@Param("productOptionItemId") Long productOptionItemId);

    void deleteByProductOptionId(@Param("productOptionId") Long productOptionId);
}
