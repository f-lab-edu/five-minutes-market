package kr.fiveminutesmarket.category.service;

import kr.fiveminutesmarket.category.domain.MainCategory;
import kr.fiveminutesmarket.category.domain.SubCategory;
import kr.fiveminutesmarket.category.dto.request.SubCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;
import kr.fiveminutesmarket.category.error.exception.ParentCategoryNotExistedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNameDuplicatedException;
import kr.fiveminutesmarket.category.error.exception.SubCategoryNotFoundException;
import kr.fiveminutesmarket.category.repository.MainCategoryRepository;
import kr.fiveminutesmarket.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    private final MainCategoryRepository mainCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository, MainCategoryRepository mainCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.mainCategoryRepository = mainCategoryRepository;
    }

    public List<SubCategoryResponse> findAll() {

        List<SubCategory> subCategoryList = subCategoryRepository.findAll();

        return subCategoryList.stream()
                .map(SubCategory::toResponse)
                .collect(Collectors.toList());
    }

    public SubCategoryResponse findById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id);

        if(subCategory == null)
            throw new SubCategoryNotFoundException(id);

        return subCategory.toResponse();
    }

    public SubCategoryResponse add(SubCategoryReqeust resource) {
        int foundNumber = subCategoryRepository.findByName(resource.getSubCategoryName());

        if(foundNumber != 0 )
            throw new SubCategoryNameDuplicatedException(resource.getSubCategoryName());

        MainCategory mainCategory = mainCategoryRepository.findById(resource.getMainCategoryId());

        if(mainCategory == null)
            throw new ParentCategoryNotExistedException(resource.getMainCategoryId());

        SubCategory subCategory = resource.toEntity();
        subCategoryRepository.insert(subCategory);

        return subCategory.toResponse();
    }

    public int update(Long id, SubCategoryReqeust resource) {

        SubCategory subCategory = subCategoryRepository.findById(id);
        subCategory.updateInfo(resource);

        return subCategoryRepository.updateSubCategory(id, subCategory);
    }

    public void deleteById(Long id) {
        subCategoryRepository.deleteById(id);
    }
}
