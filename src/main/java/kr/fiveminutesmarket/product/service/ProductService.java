package kr.fiveminutesmarket.product.service;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.request.ProductRequestDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseListDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseDTO;
import kr.fiveminutesmarket.product.error.exception.ProductNotFoundException;
import kr.fiveminutesmarket.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductRequestDTO product) {
        Product newProduct = product.toEntity();
        productRepository.insertProduct(newProduct);
    }

    public ProductResponseDTO findByProductId(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품 ID의 데이터를 찾을수 없습니다."));
    }

    public List<ProductResponseListDTO> findAll(int count, int pageNum) {
        int startIndex = count * (pageNum - 1);

        return productRepository.findAll(startIndex, count);
    }

    public void updateProduct(Long productId, ProductRequestDTO product) {
        ProductResponseDTO compareProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품 ID의 데이터를 찾을수 없습니다."));

        if (!product.getSellerId().equals(compareProduct.getSellerId())) {
            throw new IllegalArgumentException("판매자 ID가 다릅니다.");
        }

        productRepository.updateProduct(product.toEntity());
    }

    public void updateQuantity(Long productId, int quantity) {
        ProductResponseDTO compareProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품 ID의 데이터를 찾을수 없습니다."));

        productRepository.updateQuantity(productId, quantity);
    }

    public void deleteProduct(Long productId) {
        ProductResponseDTO compareProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품 ID의 데이터를 찾을수 없습니다."));

        productRepository.deleteProduct(productId);
    }

}
