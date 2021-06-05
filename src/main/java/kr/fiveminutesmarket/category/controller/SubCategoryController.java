package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.request.SubCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;
import kr.fiveminutesmarket.category.service.SubCategoryService;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<SubCategoryResponse>>> getAll() {
        List<SubCategoryResponse> subCategoryList = subCategoryService.findAll();

        return ResponseEntity.ok(new ResponseDto<>(0,null, subCategoryList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<SubCategoryResponse>> findById(@PathVariable("id") Long id) {
        SubCategoryResponse subCategory = subCategoryService.findById(id);

        return ResponseEntity.ok(new ResponseDto<>(0,null, subCategory));
    }

    @PostMapping
    public ResponseEntity<ResponseDto<SubCategoryResponse>> add(@RequestBody SubCategoryReqeust resource) throws URISyntaxException {
        SubCategoryResponse response = subCategoryService.add(resource);

        URI uri = new URI("/mainCategory/" + response.getMainCategoryId());
        return ResponseEntity.created(uri).body(new ResponseDto<>(0,null, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id,
                      @RequestBody SubCategoryReqeust resource) {
        subCategoryService.update(id, resource);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        subCategoryService.deleteById(id);

        return ResponseEntity.ok(new ResponseDto<>(0));
    }

}
