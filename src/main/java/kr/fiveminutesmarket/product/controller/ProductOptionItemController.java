package kr.fiveminutesmarket.product.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.product.dto.request.ProductOptionItemRequestDto;
import kr.fiveminutesmarket.product.dto.response.ProductOptionItemResponseDto;
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
    public ResponseDto<List<ProductOptionItemResponseDto>> findAll() {
        List<ProductOptionItemResponseDto> productOptionItemList = productOptionItemService.findAll();

        return new ResponseDto<>(0,null, productOptionItemList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<ProductOptionItemResponseDto> findById(@PathVariable("id") Long id) {
        ProductOptionItemResponseDto productOptionItem = productOptionItemService.findById(id);
        return new ResponseDto<>(0,null, productOptionItem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<ProductOptionItemResponseDto> add(@Valid @RequestBody ProductOptionItemRequestDto resource) throws URISyntaxException {
        ProductOptionItemResponseDto response = productOptionItemService.add(resource);

        URI uri = new URI("/productOptionItem/" + response.getProductOptionItemId());
        return new ResponseDto<>(0,null, response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> update(@PathVariable("id") Long id,
                      @Valid @RequestBody ProductOptionItemRequestDto resource) {
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
