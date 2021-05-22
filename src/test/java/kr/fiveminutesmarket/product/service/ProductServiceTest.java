package kr.fiveminutesmarket.product.service;

import kr.fiveminutesmarket.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    @DisplayName("Product 객체를 넣어주면 데이터 저장 후 동일 ProductId 조회")
    void addProductTest() {
        Product product = new Product();
        product.setMainCategoryId(1L);
        product.setSubCategoryId(1L);
        product.setName("테스트 상품");
        product.setPrice(5000);
        product.setThumb("http://fiveminutesmarket.kr/test.png");
        product.setDetail("테스트 상품 상세 정보");

        productService.addProduct(product);

        assertThat(product.getProductId()).isNotNull();

        Product targetProduct = productService.findByProductId(product.getProductId());

        assertThat(targetProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    @DisplayName("Product 없는 데이터 조회시 Exception 발생")
    void findByProductIdTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Product targetProduct = productService.findByProductId(0L);
        });

        assertThat(exception.getClass()).isEqualTo(IllegalArgumentException.class);
    }
}