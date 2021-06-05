package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.option.dto.request.ProductOptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionItemResponse;
import kr.fiveminutesmarket.option.service.ProductOptionItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDto<List<ProductOptionItemResponse>>> findAll() {
        List<ProductOptionItemResponse> productOptionItemList = productOptionItemService.findAll();

        return ResponseEntity.ok(new ResponseDto<>(0,null, productOptionItemList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductOptionItemResponse>> findById(@PathVariable("id") Long id) {
        ProductOptionItemResponse productOptionItem = productOptionItemService.findById(id);
        return ResponseEntity.ok(new ResponseDto<>(0,null, productOptionItem));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ProductOptionItemResponse>> add(@RequestBody ProductOptionItemRequest resource) throws URISyntaxException {
        ProductOptionItemResponse response = productOptionItemService.add(resource);

        URI uri = new URI("/productOptionItem/" + response.getProductOptionId());
        return ResponseEntity.created(uri).body(new ResponseDto<>(0,null, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id,
                      @RequestBody ProductOptionItemRequest resource) {
        productOptionItemService.update(id, resource);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productOptionItemService.deleteById(id);
        return ResponseEntity.ok(new ResponseDto<>(0));
    }
}
