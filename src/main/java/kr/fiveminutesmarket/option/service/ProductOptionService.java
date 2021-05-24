package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.ProductOption;
import kr.fiveminutesmarket.option.dto.ProductOptionRequest;
import kr.fiveminutesmarket.option.dto.ProductOptionResponse;
import kr.fiveminutesmarket.option.mapper.ProductOptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductOptionService {

    private final ProductOptionMapper productOptionMapper;

    public ProductOptionService(ProductOptionMapper optionMapper) {
        this.productOptionMapper = optionMapper;
    }

    public List<ProductOptionResponse> findAll() {
        List<ProductOption> productOptionList = productOptionMapper.findAll();

        return productOptionList.stream().map(ProductOption::toResponse)
                .collect(Collectors.toList());
    }

    public ProductOptionResponse findById(Long id) {
        ProductOption productOption = productOptionMapper.findByProductOptionId(id);

        return productOption.toResponse();
    }

    public ProductOptionResponse add(ProductOptionRequest resource) {
        ProductOption productOption = resource.toEntity();
        productOptionMapper.insert(productOption);

        return productOption.toResponse();
    }

    public int update(Long id, ProductOptionRequest resource) {

        ProductOption productOption = productOptionMapper.findByProductOptionId(id);
        productOption.updateInfo(resource);

        return productOptionMapper.updateProductOption(id, productOption);
    }

}
