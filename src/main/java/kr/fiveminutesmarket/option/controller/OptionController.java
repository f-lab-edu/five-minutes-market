package kr.fiveminutesmarket.option.controller;

import kr.fiveminutesmarket.option.dto.OptionRequest;
import kr.fiveminutesmarket.option.dto.OptionResponse;
import kr.fiveminutesmarket.option.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/option")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("")
    public List<OptionResponse> findAll() {
        return optionService.findAll();
    }

    @GetMapping("/{id}")
    public OptionResponse findById(@PathVariable("id") Long id) {
        return optionService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<OptionResponse> add(@RequestBody OptionRequest resource) throws URISyntaxException {
        OptionResponse response = optionService.add(resource);

        URI uri = new URI("/option/" + response.getOptionId());
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") Long id,
                      @RequestBody OptionRequest resource) {
        return optionService.update(id, resource);
    }
}
