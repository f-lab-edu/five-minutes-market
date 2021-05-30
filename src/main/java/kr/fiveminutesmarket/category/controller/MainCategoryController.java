package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.request.MainCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;
import kr.fiveminutesmarket.category.service.MainCategoryService;
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
    public List<MainCategoryResponse> findAll() {
        return mainCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public MainCategoryResponse findById(@PathVariable("id") Long id) {
        return mainCategoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<MainCategoryResponse> insert(@RequestBody MainCategoryReqeust resource) throws URISyntaxException {
        MainCategoryResponse response = mainCategoryService.add(resource);

        URI uri = new URI("/mainCategory/" + response.getMainCategoryId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id,
                      @RequestBody MainCategoryReqeust resource) {
        return mainCategoryService.update(id, resource);
    }

}
