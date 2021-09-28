package kr.fiveminutesmarket.category.service;

import kr.fiveminutesmarket.category.domain.MainCategory;
import kr.fiveminutesmarket.category.domain.SubCategory;
import kr.fiveminutesmarket.category.dto.request.SubCategoryRequest;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;
import kr.fiveminutesmarket.category.error.exception.MainCategoryNotFoundException;
import kr.fiveminutesmarket.category.error.exception.ParentCategoryNotExistedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNotFoundException;
import kr.fiveminutesmarket.category.repository.MainCategoryRepository;
import kr.fiveminutesmarket.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    private final MainCategoryRepository mainCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, MainCategoryRepository mainCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.mainCategoryRepository = mainCategoryRepository;
    }

    public SubCategoryResponse add(SubCategoryRequest resource) {
        long count = subCategoryRepository.countBySubCategoryName(resource.getSubCategoryName());

        if(count != 0 )
            throw new SubCategoryNameDuplicatedException(resource.getSubCategoryName());

        MainCategory mainCategory = mainCategoryRepository.findById(resource.getMainCategoryId())
                .orElseThrow(() -> new ParentCategoryNotExistedException(resource.getMainCategoryId()));

        SubCategory subCategory = toEntity(resource, mainCategory);

        SubCategory saved = subCategoryRepository.save(subCategory);

        return toResponse(saved);
    }

    public void update(Long id, SubCategoryRequest resource) {

        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new SubCategoryNotFoundException(id));

        MainCategory mainCategory = mainCategoryRepository.findById(resource.getMainCategoryId())
                .orElseThrow(() -> new MainCategoryNotFoundException(resource.getMainCategoryId()));

        subCategory.updateInfo(resource.getSubCategoryName(), mainCategory);
    }

    public void deleteById(Long id) {
        subCategoryRepository.deleteById(id);
    }

    private SubCategory toEntity(SubCategoryRequest resource, MainCategory mainCategory) {
        return new SubCategory(resource.getSubCategoryName(), mainCategory);
    }

    public static SubCategoryResponse toResponse(SubCategory entity) {
        return new SubCategoryResponse(
                entity.getSubCategoryId(),
                entity.getSubCategoryName());
    }
}
