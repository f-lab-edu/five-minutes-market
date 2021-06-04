package kr.fiveminutesmarket.product.controller;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.request.ProductRequestDTO;
import kr.fiveminutesmarket.product.dto.response.ProductListResponseDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseDTO;
import kr.fiveminutesmarket.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable long productId) {
        ProductResponseDTO product = productService.findByProductId(productId);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addProduct(@Valid @RequestBody ProductRequestDTO product) {
        productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductListResponseDTO>> getProductList(@RequestParam(value = "count", required = true) int count,
                                                                       @RequestParam(value = "page_num", required = true) int pageNum) {
        List<ProductListResponseDTO> products = productService.findAll(count, pageNum);

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable Long productId,
                                                    @Valid @RequestBody ProductRequestDTO product) {
        productService.updateProduct(productId, product);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{productId}/{quantity}")
    public ResponseEntity<HttpStatus> updateQuantity(@PathVariable Long productId,
                                                     @Positive @PathVariable int quantity) {
        productService.updateQuantity(productId, quantity);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}