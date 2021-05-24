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
        product.setSellerId("hkx2r0i");
        product.setQuantity(10);
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

    @Test
    @DisplayName("일치하는 ProductId가 있으면 해당 상품 업데이트")
    void updateProductTest() {
        Product product = new Product();
        product.setProductId(1L);
        product.setMainCategoryId(1L);
        product.setSubCategoryId(1L);
        product.setSellerId("hkx2r0i2");
        product.setQuantity(100);
        product.setName("테스트 상품222");
        product.setPrice(5500);
        product.setThumb("http://fiveminutesmarket.kr/test.png");
        product.setDetail("테스트 상품 상세 정보222222222");

        productService.updateProduct(product);

        Product targetProduct = productService.findByProductId(product.getProductId());

        assertThat(targetProduct.getName()).isEqualTo(product.getName());
    }

    @Test
    @DisplayName("일치하는 ProductId가 있으면 해당 상품 재고 업데이트")
    void updateQuantityTest() {
        Long productId = 1L;
        int quantity = 1500;

        productService.updateQuantity(productId, quantity);

        Product targetProduct = productService.findByProductId(productId);

        assertThat(targetProduct.getQuantity()).isEqualTo(quantity);
    }

    @Test
    @DisplayName("Product 생성 후 해당 상품 삭제")
    void deleteProductTest() {
        Product product = new Product();
        product.setMainCategoryId(1L);
        product.setSubCategoryId(1L);
        product.setSellerId("hkx2r0i");
        product.setQuantity(10);
        product.setName("테스트 상품");
        product.setPrice(5000);
        product.setThumb("http://fiveminutesmarket.kr/test.png");
        product.setDetail("테스트 상품 상세 정보");

        productService.addProduct(product);

        assertThat(product.getProductId()).isNotNull();

        productService.deleteProduct(product.getProductId());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Product targetProduct = productService.findByProductId(product.getProductId());
        });
    }
}