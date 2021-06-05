package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.option.dto.request.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionResponse;
import kr.fiveminutesmarket.option.service.ProductOptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDto<List<ProductOptionResponse>>> findAll() {
        List<ProductOptionResponse> productOptionList = productOptionService.findAll();

        return ResponseEntity.ok(new ResponseDto<>(0,null, productOptionList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductOptionResponse>> findById(@PathVariable("id") Long id) {
        ProductOptionResponse productOption = productOptionService.findById(id);

        return ResponseEntity.ok(new ResponseDto<>(0,null, productOption));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ProductOptionResponse>> add(@RequestBody ProductOptionRequest resource) throws URISyntaxException {
        ProductOptionResponse response = productOptionService.add(resource);

        URI uri = new URI("/productOption/" + response.getProductOptionId());
        return ResponseEntity.created(uri).body(new ResponseDto<>(0,null, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id,
                      @RequestBody ProductOptionRequest resource) {
        productOptionService.update(id, resource);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        productOptionService.deleteById(id);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

}
