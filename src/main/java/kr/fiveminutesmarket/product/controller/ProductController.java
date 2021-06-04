package kr.fiveminutesmarket.product.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.request.ProductRequestDTO;
import kr.fiveminutesmarket.product.dto.response.ProductListResponseDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseDTO;
import kr.fiveminutesmarket.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDto<ProductResponseDTO>> getProduct(@PathVariable long productId) {
        ProductResponseDTO product = productService.findByProductId(productId);

        return ResponseEntity.ok(new ResponseDto<>(0,null, product));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addProduct(@RequestBody ProductRequestDTO product) {
        productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto<>(0));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductListResponseDTO>>> getProductList(@RequestParam(value = "count", required = true) int count,
                                                                       @RequestParam(value = "page_num", required = true) int pageNum) {
        List<ProductListResponseDTO> products = productService.findAll(count, pageNum);

        return ResponseEntity.ok(new ResponseDto<>(0,null, products));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO product) {
        productService.updateProduct(productId, product);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

    @PatchMapping("/{productId}/{quantity}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable Long productId, @PathVariable int quantity) {
        productService.updateQuantity(productId, quantity);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }
}