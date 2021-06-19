package kr.fiveminutesmarket.product.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.dto.request.ProductRequestDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseListDTO;
import kr.fiveminutesmarket.product.dto.response.ProductResponseDTO;
import kr.fiveminutesmarket.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<ProductResponseDTO> getProduct(@PathVariable long productId) {
        ProductResponseDTO product = productService.findByProductId(productId);

        return new ResponseDto<>(0,null, product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<?> addProduct(@Valid @RequestBody ProductRequestDTO product) {
        productService.addProduct(product);

        return new ResponseDto<>(0);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<ProductResponseListDTO>> getProductList(@RequestParam(value = "count", required = true) int count,
                                                                    @RequestParam(value = "page_num", required = true) int pageNum) {
        List<ProductResponseListDTO> products = productService.findAll(count, pageNum);

        return new ResponseDto<>(0,null, products);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> updateProduct(@PathVariable Long productId,
                                        @Valid @RequestBody ProductRequestDTO product) {
        productService.updateProduct(productId, product);

        return new ResponseDto<>(0);
    }

    @PatchMapping("/{productId}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> updateQuantity(@PathVariable Long productId, @PathVariable int quantity) {
        productService.updateQuantity(productId, quantity);

        return new ResponseDto<>(0);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return new ResponseDto<>(0);
    }
}