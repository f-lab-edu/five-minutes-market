package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.request.MainCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;
import kr.fiveminutesmarket.category.service.MainCategoryService;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<MainCategoryResponse>> findAll() {
        List<MainCategoryResponse> mainCategoryList = mainCategoryService.findAll();

        return new ResponseDto<>(0,null, mainCategoryList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<MainCategoryResponse> findById(@PathVariable("id") Long id) {
        MainCategoryResponse mainCategory = mainCategoryService.findById(id);

        return new ResponseDto<>(0,null, mainCategory);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<MainCategoryResponse> insert(@RequestBody MainCategoryReqeust resource) {
        MainCategoryResponse response = mainCategoryService.add(resource);

        return new ResponseDto<>(0, null, response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> update(@PathVariable("id") Long id,
                      @RequestBody MainCategoryReqeust resource) {
        mainCategoryService.update(id, resource);

        return new ResponseDto<>(0);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> delete(@PathVariable("id") Long id) {
        mainCategoryService.deleteById(id);

        return new ResponseDto<>(0);
    }

}
