package kr.fiveminutesmarket.product.controller;

import kr.fiveminutesmarket.product.domain.Product;
import kr.fiveminutesmarket.product.dto.ProductListDto;
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
    public ResponseEntity<Product> getProduct(@PathVariable long productId) {
        Product product = productService.findByProductId(productId);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addProduct(@RequestBody Product product) {
        productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductListDto>> getProductList(@RequestParam(value = "count", required = true) int count,
                                                               @RequestParam(value = "page_num", required = true) int pageNum) {
        List<ProductListDto> products = productService.findAll(count, pageNum);

        return ResponseEntity.ok(products);
    }
}
