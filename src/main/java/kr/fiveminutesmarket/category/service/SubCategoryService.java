package kr.fiveminutesmarket.category.service;

import kr.fiveminutesmarket.category.domain.SubCategory;
import kr.fiveminutesmarket.category.dto.request.SubCategoryReqeust;
import kr.fiveminutesmarket.category.dto.response.SubCategoryResponse;
import kr.fiveminutesmarket.category.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubCategoryService {

    private final SubCategoryRepository subCategoryMapper;

    public SubCategoryService(SubCategoryRepository subCategoryMapper) {
        this.subCategoryMapper = subCategoryMapper;
    }

    public List<SubCategoryResponse> findAll() {

        List<SubCategory> subCategoryList = subCategoryMapper.findAll();

        return subCategoryList.stream()
                .map(SubCategory::toResponse)
                .collect(Collectors.toList());
    }

    public SubCategoryResponse findById(Long id) {

        SubCategory subCategory = subCategoryMapper.findById(id);

        return subCategory.toResponse();
    }

    public SubCategoryResponse add(SubCategoryReqeust request) {
        SubCategory subCategory = request.toEntity();
        subCategoryMapper.insert(subCategory);

        return subCategory.toResponse();
    }

    public int update(Long id, SubCategoryReqeust resource) {

        SubCategory subCategory = subCategoryMapper.findById(id);
        subCategory.updateInfo(resource);

        return subCategoryMapper.updateSubCategory(id, subCategory);
    }

    public void deleteById(Long id) {
        subCategoryMapper.deleteById(id);
    }
}
