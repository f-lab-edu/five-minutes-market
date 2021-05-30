package kr.fiveminutesmarket.category.service;

import kr.fiveminutesmarket.category.domain.MainCategory;
import kr.fiveminutesmarket.category.dto.request.MainCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;
import kr.fiveminutesmarket.category.repository.MainCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MainCategoryService {

    private final MainCategoryRepository mainCategoryMapper;

    public MainCategoryService(MainCategoryRepository mainCategoryMapper) {
        this.mainCategoryMapper = mainCategoryMapper;
    }

    public List<MainCategoryResponse> findAll() {

        List<MainCategory> mainCategoryList = mainCategoryMapper.findAll();

        return mainCategoryList.stream()
                .map(MainCategory::toResponse)
                .collect(Collectors.toList());
    }

    public MainCategoryResponse findById(Long id) {

        MainCategory mainCategory = mainCategoryMapper.findById(id);

        return mainCategory.toResponse();
    }

    public MainCategoryResponse add(MainCategoryReqeust request) {

        MainCategory mainCategory = request.toEntity();
        mainCategoryMapper.insert(mainCategory);

        return mainCategory.toResponse();
    }

    public int update(Long id, MainCategoryReqeust resource) {

        MainCategory mainCategory = mainCategoryMapper.findById(id);
        mainCategory.updateInfo(resource);

        return mainCategoryMapper.updateMainCategory(id, mainCategory);
    }

    public void deleteById(Long id) {
        mainCategoryMapper.deleteById(id);
    }
    
}
