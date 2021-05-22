package kr.fiveminutesmarket.product.repository;

import kr.fiveminutesmarket.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface ProductRepository {

    void insertProduct(Product product);

    Optional<Product> findByProductId(Long productId);

}
