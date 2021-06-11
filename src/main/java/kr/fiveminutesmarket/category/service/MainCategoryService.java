package kr.fiveminutesmarket.category.service;

import kr.fiveminutesmarket.category.domain.MainCategory;
import kr.fiveminutesmarket.category.dto.request.MainCategoryRequest;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNotFoundException;
import kr.fiveminutesmarket.category.repository.MainCategoryRepository;
import kr.fiveminutesmarket.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    public MainCategoryService(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<MainCategoryResponse> findAll() {

        List<MainCategory> mainCategoryList = mainCategoryRepository.findAll();

        return mainCategoryList.stream()
                .map(MainCategory::toResponse)
                .collect(Collectors.toList());
    }

    public MainCategoryResponse findById(Long id) {
        MainCategory mainCategory = mainCategoryRepository.findById(id);

        if(mainCategory == null)
            throw new MainCategoryNotFoundException(id);

        return mainCategory.toResponse();
    }

    public MainCategoryResponse add(MainCategoryRequest resource) {
        int count = mainCategoryRepository.countByName(resource.getMainCategoryName());

        if(count != 0 )
            throw new MainCategoryNameDuplicatedException(resource.getMainCategoryName());

        MainCategory mainCategory = resource.toEntity();
        mainCategoryRepository.insert(mainCategory);

        return mainCategory.toResponse();
    }

    public int update(Long id, MainCategoryRequest resource) {

        MainCategory mainCategory = mainCategoryRepository.findById(id);
        mainCategory.updateInfo(resource);

        return mainCategoryRepository.updateMainCategory(id, mainCategory);
    }

    public void deleteById(Long id) {
        subCategoryRepository.deleteByMainCategoryId(id);

        mainCategoryRepository.deleteById(id);
    }

}
