package kr.fiveminutesmarket.product.repository;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.response.ProductListResponseDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductRepository {

    void insertProduct(Product product);

    Optional<ProductResponseDTO> findByProductId(Long productId);

    List<ProductListResponseDTO> findAll(@Param("startIndex") int startIndex, @Param("itemCount") int itemCount);

    void updateProduct(Product product);

    void updateQuantity(@Param("productId") Long productId,@Param("quantity") int quantity);

    void deleteProduct(@Param("productId") Long productId);

}
