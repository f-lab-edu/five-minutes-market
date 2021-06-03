package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.ProductOption;
import kr.fiveminutesmarket.option.dto.request.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionResponse;
import kr.fiveminutesmarket.option.error.exception.ProductOptionNotFoundException;
import kr.fiveminutesmarket.option.repository.ProductOptionItemRepository;
import kr.fiveminutesmarket.option.repository.ProductOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    private final ProductOptionItemRepository productOptionItemRepository;

    public ProductOptionService(ProductOptionRepository productOptionRepository,
                                ProductOptionItemRepository productOptionItemRepository) {
        this.productOptionRepository = productOptionRepository;
        this.productOptionItemRepository = productOptionItemRepository;
    }

    public List<ProductOptionResponse> findAll() {
        List<ProductOption> productOptionList = productOptionRepository.findAll();

        return productOptionList.stream().map(ProductOption::toResponse)
                .collect(Collectors.toList());
    }

    public ProductOptionResponse findById(Long id) {
        ProductOption productOption = productOptionRepository.findById(id);

        if(productOption == null)
            throw new ProductOptionNotFoundException(id);

        return productOption.toResponse();
    }

    public ProductOptionResponse add(ProductOptionRequest resource) {
        ProductOption productOption = resource.toEntity();
        productOptionRepository.insert(productOption);

        return productOption.toResponse();
    }

    public int update(Long id, ProductOptionRequest resource) {

        ProductOption productOption = productOptionRepository.findById(id);
        productOption.updateInfo(resource);

        return productOptionRepository.updateProductOption(id, productOption);
    }

    public void deleteById(Long id) {
        productOptionItemRepository.deleteByProductOptionId(id);

        productOptionRepository.deleteById(id);
    }

}
