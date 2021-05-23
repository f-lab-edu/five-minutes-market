package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.option.dto.OptionItemRequest;
import kr.fiveminutesmarket.option.dto.OptionItemResponse;
import kr.fiveminutesmarket.option.dto.OptionRequest;
import kr.fiveminutesmarket.option.dto.OptionResponse;
import kr.fiveminutesmarket.option.service.OptionItemService;
import kr.fiveminutesmarket.option.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/optionItem")
public class OptionItemController {

    private final OptionItemService optionItemService;

    public OptionItemController(OptionItemService optionItemService) {
        this.optionItemService = optionItemService;
    }

    @GetMapping("")
    public List<OptionItemResponse> findAll() {
        return optionItemService.findAll();
    }

    @GetMapping("/{id}")
    public OptionItemResponse findById(@PathVariable("id") Long id) {
        return optionItemService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<OptionItemResponse> add(@RequestBody OptionItemRequest resource) throws URISyntaxException {
        OptionItemResponse response = optionItemService.add(resource);

        URI uri = new URI("/option/" + response.getOptionId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id,
                      @RequestBody OptionItemRequest resource) {
        return optionItemService.update(id, resource);
    }
}
