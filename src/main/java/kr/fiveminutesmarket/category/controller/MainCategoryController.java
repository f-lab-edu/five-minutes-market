package kr.fiveminutesmarket.category.controller;

import kr.fiveminutesmarket.category.dto.MainCategoryReqeust;
import kr.fiveminutesmarket.category.dto.MainCategoryResponse;
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

    @GetMapping("")
    public List<MainCategoryResponse> findAll() {
        return mainCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public MainCategoryResponse getById(@PathVariable("id") Long id) {
        return mainCategoryService.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<MainCategoryResponse> insert(@RequestBody MainCategoryReqeust request) throws URISyntaxException {
        MainCategoryResponse response = mainCategoryService.add(request);

        URI uri = new URI("/mainCategory/" + response.getMainCategoryId());
        return ResponseEntity.created(uri).body(response);
    }

}
