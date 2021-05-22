package kr.fiveminutesmarket.product.service;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품 ID의 데이터를 찾을수 없습니다."));
    }

}
