package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.SubCategoryReqeust;
import kr.fiveminutesmarket.category.dto.SubCategoryResponse;
import kr.fiveminutesmarket.category.service.SubCategoryService;
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

    @GetMapping("")
    public List<SubCategoryResponse> getAll() {
        return subCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public SubCategoryResponse findById(@PathVariable("id") Long id) {
        return subCategoryService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<SubCategoryResponse> add(@RequestBody SubCategoryReqeust resource) throws URISyntaxException {
        SubCategoryResponse response = subCategoryService.add(resource);

        URI uri = new URI("/mainCategory/" + response.getMainCategoryId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id,
                      @RequestBody SubCategoryReqeust resource) {
        return subCategoryService.update(id, resource);
    }

}
