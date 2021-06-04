package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.option.dto.request.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionResponse;
import kr.fiveminutesmarket.option.service.ProductOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/productOption")
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    public ProductOptionController(ProductOptionService productOptionService) {
        this.productOptionService = productOptionService;
    }

    @GetMapping
    public List<ProductOptionResponse> findAll() {
        return productOptionService.findAll();
    }

    @GetMapping("/{id}")
    public ProductOptionResponse findById(@PathVariable("id") Long id) {
        return productOptionService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ProductOptionResponse> add(@Valid @RequestBody ProductOptionRequest resource) throws URISyntaxException {
        ProductOptionResponse response = productOptionService.add(resource);

        URI uri = new URI("/productOption/" + response.getProductOptionId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id,
                      @Valid @RequestBody ProductOptionRequest resource) {
        return productOptionService.update(id, resource);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        productOptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
