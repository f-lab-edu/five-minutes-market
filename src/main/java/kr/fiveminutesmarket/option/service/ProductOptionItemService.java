package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.ProductOption;
import kr.fiveminutesmarket.option.domain.ProductOptionItem;
import kr.fiveminutesmarket.option.dto.request.ProductOptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionItemResponse;
import kr.fiveminutesmarket.option.error.exception.ParentProductOptionNotExistedException;
import kr.fiveminutesmarket.option.error.exception.ProductOptionItemNameDuplicatedException;
import kr.fiveminutesmarket.option.error.exception.ProductOptionItemNotFoundException;
import kr.fiveminutesmarket.option.repository.ProductOptionItemRepository;
import kr.fiveminutesmarket.option.repository.ProductOptionRepository;
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

    public List<ProductOptionItemResponse> findAll() {
        List<ProductOptionItem> productOptionItemList = productOptionItemRepository.findAll();

        return productOptionItemList.stream()
                .map(ProductOptionItem::toResponse)
                .collect(Collectors.toList());
    }

    public ProductOptionItemResponse findById(Long id) {
        ProductOptionItem productOptionItem = productOptionItemRepository.findById(id);

        if(productOptionItem == null)
            throw new ProductOptionItemNotFoundException(id);

        return productOptionItem.toResponse();
    }

    public ProductOptionItemResponse add(ProductOptionItemRequest resource) {
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

    public int update(Long id, ProductOptionItemRequest resource) {

        ProductOptionItem productOptionItem = productOptionItemRepository.findById(id);
        productOptionItem.updateInfo(resource);

        return productOptionItemRepository.update(id, productOptionItem);
    }

    public void deleteById(Long id) {
        productOptionItemRepository.deleteById(id);
    }
}
