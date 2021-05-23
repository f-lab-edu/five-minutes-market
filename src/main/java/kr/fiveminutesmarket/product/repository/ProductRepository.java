package kr.fiveminutesmarket.product.repository;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.ProductListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductRepository {

    void insertProduct(Product product);

    Optional<Product> findByProductId(Long productId);

    List<ProductListDto> findAll(@Param("startIndex") int startIndex, @Param("itemCount") int itemCount);

}
