package kr.fiveminutesmarket.product.service;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.request.ProductRequestDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseDTO;
import kr.fiveminutesmarket.product.error.exception.ProductNotFoundException;
import kr.fiveminutesmarket.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("Product 객체를 넣어주면 데이터 저장 후 동일 ProductId 조회")
    void addProductTest() {
        ProductRequestDTO product = new ProductRequestDTO();
        product.setMainCategoryId(1L);
        product.setSubCategoryId(1L);
        product.setSellerId("hkx2r0i5");
        product.setQuantity(10);
        product.setName("테스트 상품");
        product.setPrice(5000);
        product.setThumb("http://fiveminutesmarket.kr/test.png");
        product.setDetail("테스트 상품 상세 정보");

        productService.addProduct(product);

    }

    @Test
    @DisplayName("Product 없는 데이터 조회시 Exception 발생")
    void findByProductIdTest() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            ProductResponseDTO targetProduct = productService.findByProductId(0L);
        });

        assertThat(exception.getClass()).isEqualTo(ProductNotFoundException.class);
    }

    @Test
    @DisplayName("일치하는 ProductId가 있으면 해당 상품 업데이트")
    void updateProductTest() {
        Product product = new Product(1L, 1L, "test",100, "아아아아아", 500, "https://www.naver.com", "dddddddddddddd");

        productRepository.insertProduct(product);

        ProductRequestDTO updateProduct = new ProductRequestDTO();
        updateProduct.setMainCategoryId(1L);
        updateProduct.setSubCategoryId(1L);
        updateProduct.setSellerId("test");
        updateProduct.setName("테스트중");
        updateProduct.setPrice(5000);
        updateProduct.setThumb("test");
        updateProduct.setDetail("dddddddddddddddddddd");

        productService.updateProduct(product.getProductId(), updateProduct);

        ProductResponseDTO compareProduct = productService.findByProductId(product.getProductId());

        assertThat(compareProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    @DisplayName("일치하는 ProductId가 있으면 해당 상품 재고 업데이트")
    void updateQuantityTest() {
        Product product = new Product(1L, 1L, "test",100, "아아아아아", 500, "https://www.naver.com", "dddddddddddddd");

        productRepository.insertProduct(product);

        Long productId = product.getProductId();
        int quantity = 1500;

        productService.updateQuantity(productId, quantity);

        ProductResponseDTO compareProduct = productService.findByProductId(productId);

        assertThat(compareProduct.getQuantity()).isEqualTo(quantity);
    }

    @Test
    @DisplayName("Product 생성 후 해당 상품 삭제")
    void deleteProductTest() {
        Product product = new Product(1L, 1L, "test",100, "아아아아아", 500, "https://www.naver.com", "dddddddddddddd");

        productRepository.insertProduct(product);

        assertThat(product.getProductId()).isNotNull();

        productService.deleteProduct(product.getProductId());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductResponseDTO compareProduct = productService.findByProductId(product.getProductId());
        });
    }
}