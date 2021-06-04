package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.option.dto.request.ProductOptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionItemResponse;
import kr.fiveminutesmarket.option.service.ProductOptionItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/productOptionItem")
public class ProductOptionItemController {

    private final ProductOptionItemService productOptionItemService;

    public ProductOptionItemController(ProductOptionItemService productOptionItemService) {
        this.productOptionItemService = productOptionItemService;
    }

    @GetMapping
    public List<ProductOptionItemResponse> findAll() {
        return productOptionItemService.findAll();
    }

    @GetMapping("/{id}")
    public ProductOptionItemResponse findById(@PathVariable("id") Long id) {
        return productOptionItemService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ProductOptionItemResponse> add(@Valid @RequestBody ProductOptionItemRequest resource) throws URISyntaxException {
        ProductOptionItemResponse response = productOptionItemService.add(resource);

        URI uri = new URI("/productOptionItem/" + response.getProductOptionId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id,
                      @Valid @RequestBody ProductOptionItemRequest resource) {
        return productOptionItemService.update(id, resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productOptionItemService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
