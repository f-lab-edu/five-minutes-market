package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.option.dto.request.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionResponse;
import kr.fiveminutesmarket.option.service.ProductOptionService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<ProductOptionResponse>> findAll() {
        List<ProductOptionResponse> productOptionList = productOptionService.findAll();

        return new ResponseDto<>(0,null, productOptionList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<ProductOptionResponse> findById(@PathVariable("id") Long id) {
        ProductOptionResponse productOption = productOptionService.findById(id);

        return new ResponseDto<>(0,null, productOption);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<ProductOptionResponse> add(@RequestBody ProductOptionRequest resource) {
        ProductOptionResponse response = productOptionService.add(resource);

        return new ResponseDto<>(0,null, response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> update(@PathVariable("id") Long id,
                      @RequestBody ProductOptionRequest resource) {
        productOptionService.update(id, resource);

        return new ResponseDto<>(0);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> delete(@PathVariable("id") Long id) {
        productOptionService.deleteById(id);

        return new ResponseDto<>(0);
    }

}
