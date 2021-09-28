package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.request.SubCategoryRequest;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;
import kr.fiveminutesmarket.category.service.SubCategoryService;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
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

        return new ResponseDto<>(0, "수정 완료하였습니다.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> delete(@PathVariable Long id) {
        subCategoryService.deleteById(id);

        return new ResponseDto<>(0);
    }

}
