package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.request.SubCategoryRequest;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;
import kr.fiveminutesmarket.category.service.SubCategoryService;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<SubCategoryResponse>> getAll() {
        List<SubCategoryResponse> subCategoryList = subCategoryService.findAll();

        return new ResponseDto<>(0,null, subCategoryList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<SubCategoryResponse> findById(@PathVariable("id") Long id) {
        SubCategoryResponse subCategory = subCategoryService.findById(id);

        return new ResponseDto<>(0,null, subCategory);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<SubCategoryResponse> add(@Valid @RequestBody SubCategoryRequest resource) {
        SubCategoryResponse response = subCategoryService.add(resource);

        return new ResponseDto<>(0,null, response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> update(@PathVariable("id") Long id,
                @Valid @RequestBody SubCategoryRequest resource) {
        subCategoryService.update(id, resource);

        return new ResponseDto<>(0);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> delete(@PathVariable Long id) {
        subCategoryService.deleteById(id);

        return new ResponseDto<>(0);
    }

}
