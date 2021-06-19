package kr.fiveminutesmarket.product.service;

import kr.fiveminutesmarket.product.domain.ProductOption;
import kr.fiveminutesmarket.product.domain.ProductOptionItem;
import kr.fiveminutesmarket.product.dto.request.ProductOptionItemRequestDto;
import kr.fiveminutesmarket.product.dto.response.ProductOptionItemResponseDto;
import kr.fiveminutesmarket.product.error.exception.ParentProductOptionNotExistedException;
import kr.fiveminutesmarket.product.error.exception.ProductOptionItemNameDuplicatedException;
import kr.fiveminutesmarket.product.error.exception.ProductOptionItemNotFoundException;
import kr.fiveminutesmarket.product.repository.ProductOptionItemRepository;
import kr.fiveminutesmarket.product.repository.ProductOptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductOptionItemService {

    private final ProductOptionItemRepository productOptionItemRepository;

    private final ProductOptionRepository productOptionRepository;

    public ProductOptionItemService(ProductOptionItemRepository productOptionItemRepository, ProductOptionRepository productOptionRepository) {
        this.productOptionItemRepository = productOptionItemRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public List<ProductOptionItemResponseDto> findAll() {
        List<ProductOptionItem> productOptionItemList = productOptionItemRepository.findAll();

        return productOptionItemList.stream()
                .map(ProductOptionItem::toResponse)
                .collect(Collectors.toList());
    }

    public ProductOptionItemResponseDto findById(Long id) {
        ProductOptionItem productOptionItem = productOptionItemRepository.findById(id);

        if(productOptionItem == null)
            throw new ProductOptionItemNotFoundException(id);

        return productOptionItem.toResponse();
    }

    public ProductOptionItemResponseDto add(ProductOptionItemRequestDto resource) {
        int count = productOptionItemRepository.countByName(resource.getProductOptionItemName());

        if(count != 0)
            throw new ProductOptionItemNameDuplicatedException(resource.getProductOptionItemName());

        ProductOption productOption = productOptionRepository.findById(resource.getProductOptionId());

        if(productOption == null)
            throw new ParentProductOptionNotExistedException(resource.getProductOptionId());

        ProductOptionItem productOptionItem = resource.toEntity();
        productOptionItemRepository.insert(productOptionItem);

        return productOptionItem.toResponse();
    }

    public int update(Long id, ProductOptionItemRequestDto resource) {

        ProductOptionItem productOptionItem = productOptionItemRepository.findById(id);
        productOptionItem.updateInfo(resource);

        return productOptionItemRepository.update(id, productOptionItem);
    }

    public void deleteById(Long id) {
        productOptionItemRepository.deleteById(id);
    }
}
