package kr.fiveminutesmarket.product.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.dto.request.ProductOptionItemRequest;
import kr.fiveminutesmarket.product.dto.response.ProductOptionItemResponse;
import kr.fiveminutesmarket.product.service.ProductOptionItemService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<ProductOptionItemResponse>> findAll() {
        List<ProductOptionItemResponse> productOptionItemList = productOptionItemService.findAll();

        return new ResponseDto<>(0,null, productOptionItemList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<ProductOptionItemResponse> findById(@PathVariable("id") Long id) {
        ProductOptionItemResponse productOptionItem = productOptionItemService.findById(id);
        return new ResponseDto<>(0,null, productOptionItem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<ProductOptionItemResponse> add(@Valid @RequestBody ProductOptionItemRequest resource) throws URISyntaxException {
        ProductOptionItemResponse response = productOptionItemService.add(resource);

        URI uri = new URI("/productOptionItem/" + response.getProductOptionId());
        return new ResponseDto<>(0,null, response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> update(@PathVariable("id") Long id,
                      @Valid @RequestBody ProductOptionItemRequest resource) {
        productOptionItemService.update(id, resource);

        return new ResponseDto<>(0);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> delete(@PathVariable Long id) {
        productOptionItemService.deleteById(id);
        return new ResponseDto<>(0);
    }
}
