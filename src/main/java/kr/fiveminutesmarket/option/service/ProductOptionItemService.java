package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.ProductOptionItem;
import kr.fiveminutesmarket.option.dto.request.ProductOptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.ProductOptionItemResponse;
import kr.fiveminutesmarket.option.repository.ProductOptionItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductOptionItemService {

    private final ProductOptionItemRepository productOptionItemMapper;

    public ProductOptionItemService(ProductOptionItemRepository productOptionItemMapper) {
        this.productOptionItemMapper = productOptionItemMapper;
    }

    public List<ProductOptionItemResponse> findAll() {
        List<ProductOptionItem> productOptionItemList = productOptionItemMapper.findAll();

        return productOptionItemList.stream()
                .map(ProductOptionItem::toResponse)
                .collect(Collectors.toList());
    }

    public ProductOptionItemResponse findById(Long id) {
        ProductOptionItem productOptionItem = productOptionItemMapper.findById(id);

        return productOptionItem.toResponse();
    }

    public ProductOptionItemResponse add(ProductOptionItemRequest resource) {
        ProductOptionItem productOptionItem = resource.toEntity();
        productOptionItemMapper.insert(productOptionItem);

        return productOptionItem.toResponse();
    }

    public int update(Long id, ProductOptionItemRequest resource) {

        ProductOptionItem productOptionItem = productOptionItemMapper.findById(id);
        productOptionItem.updateInfo(resource);

        return productOptionItemMapper.update(id, productOptionItem);
    }

    public void deleteById(Long id) {
        productOptionItemMapper.deleteById(id);
    }
}
