package kr.fiveminutesmarket.product.service;

import kr.fiveminutesmarket.product.domain.ProductOption;
import kr.fiveminutesmarket.product.dto.request.ProductOptionRequestDto;
import kr.fiveminutesmarket.product.dto.response.ProductOptionResponseDto;
import kr.fiveminutesmarket.product.error.exception.ProductOptionNotFoundException;
import kr.fiveminutesmarket.product.repository.ProductOptionItemRepository;
import kr.fiveminutesmarket.product.repository.ProductOptionRepository;
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

    public List<ProductOptionResponseDto> findAll() {
        List<ProductOption> productOptionList = productOptionRepository.findAll();

        return productOptionList.stream().map(ProductOption::toResponse)
                .collect(Collectors.toList());
    }

    public ProductOptionResponseDto findById(Long id) {
        ProductOption productOption = productOptionRepository.findById(id);

        if(productOption == null)
            throw new ProductOptionNotFoundException(id);

        return productOption.toResponse();
    }

    public ProductOptionResponseDto add(ProductOptionRequestDto resource) {
        ProductOption productOption = resource.toEntity();
        productOptionRepository.insert(productOption);

        return productOption.toResponse();
    }

    public int update(Long id, ProductOptionRequestDto resource) {

        ProductOption productOption = productOptionRepository.findById(id);
        productOption.updateInfo(resource);

        return productOptionRepository.updateProductOption(id, productOption);
    }

    public void deleteById(Long id) {
        productOptionItemRepository.deleteByProductOptionId(id);

        productOptionRepository.deleteById(id);
    }

}
