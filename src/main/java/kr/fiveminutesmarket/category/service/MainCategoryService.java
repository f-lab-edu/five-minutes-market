package kr.fiveminutesmarket.category.service;

import kr.fiveminutesmarket.category.domain.MainCategory;
import kr.fiveminutesmarket.category.domain.SubCategory;
import kr.fiveminutesmarket.category.dto.request.MainCategoryRequest;
import kr.fiveminutesmarket.category.dto.response.MainCategoryResponse;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNotFoundException;
import kr.fiveminutesmarket.category.repository.MainCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class MainCategoryService {

    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryService(MainCategoryRepository mainCategoryRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
    }

    public List<MainCategoryResponse> findAll() {

        List<MainCategory> mainCategoryList = mainCategoryRepository.findAll();

        return mainCategoryList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public MainCategoryResponse findById(Long id) {
        MainCategory mainCategory = mainCategoryRepository.findById(id)
                .orElseThrow(() -> new MainCategoryNotFoundException(id));

        return toResponse(mainCategory);
    }

    public MainCategoryResponse add(MainCategoryRequest resource) {
        long count = mainCategoryRepository.countByMainCategoryName(resource.getMainCategoryName());

        if(count != 0 )
            throw new MainCategoryNameDuplicatedException(resource.getMainCategoryName());

        MainCategory mainCategory = toEntity(resource);
        MainCategory saved = mainCategoryRepository.save(mainCategory);

        return toResponse(saved);
    }

    public void update(Long id, MainCategoryRequest resource) {
        MainCategory mainCategory = mainCategoryRepository.findById(id)
                .orElseThrow(() -> new MainCategoryNotFoundException(id));

        mainCategory.updateInfo(resource);
    }

    public void deleteById(Long id) {
        mainCategoryRepository.deleteById(id);
    }

    public MainCategory toEntity(MainCategoryRequest resource) {
        return new MainCategory(resource.getMainCategoryName());
    }

    public MainCategoryResponse toResponse(MainCategory mainCategory) {
        Stream<SubCategory> subCategoryStream = Optional.ofNullable(mainCategory.getSubCategoryList())
                .stream()
                .flatMap(Collection::stream);

        return new MainCategoryResponse(mainCategory.getMainCategoryId(),
                mainCategory.getMainCategoryName(),
                subCategoryStream
                        .map(SubCategoryService::toResponse)
                        .collect(Collectors.toList()));
    }

}
