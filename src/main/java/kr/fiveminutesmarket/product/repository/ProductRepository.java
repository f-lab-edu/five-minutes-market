package kr.fiveminutesmarket.product.repository;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.ProductListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductRepository {

    void insertProduct(Product product);

    Optional<Product> findByProductId(Long productId);

    Optional<List<ProductListDto>> findAll(int startIndex, int itemCount);

}
