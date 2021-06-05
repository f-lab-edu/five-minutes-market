package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.request.MainCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;
import kr.fiveminutesmarket.category.service.MainCategoryService;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/mainCategory")
public class MainCategoryController {

    private final MainCategoryService mainCategoryService;

    public MainCategoryController(MainCategoryService mainCategoryService) {
        this.mainCategoryService = mainCategoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<MainCategoryResponse>>> findAll() {
        List<MainCategoryResponse> mainCategoryList = mainCategoryService.findAll();

        return ResponseEntity.ok(new ResponseDto<>(0,null, mainCategoryList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<MainCategoryResponse>> findById(@PathVariable("id") Long id) {
        MainCategoryResponse mainCategory = mainCategoryService.findById(id);

        return ResponseEntity.ok(new ResponseDto<>(0,null, mainCategory));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<MainCategoryResponse>> insert(@RequestBody MainCategoryReqeust resource) throws URISyntaxException {
        MainCategoryResponse response = mainCategoryService.add(resource);

        URI uri = new URI("/mainCategory/" + response.getMainCategoryId());
        return ResponseEntity.created(uri).body(new ResponseDto<>(0, null, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id,
                      @RequestBody MainCategoryReqeust resource) {
        mainCategoryService.update(id, resource);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        mainCategoryService.deleteById(id);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

}
