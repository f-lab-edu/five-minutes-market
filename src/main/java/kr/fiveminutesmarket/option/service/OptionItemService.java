package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.OptionItem;
import kr.fiveminutesmarket.option.dto.request.OptionItemRequest;
import kr.fiveminutesmarket.option.dto.response.OptionItemResponse;
import kr.fiveminutesmarket.option.repository.OptionItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OptionItemService {

    private final OptionItemRepository optionItemMapper;

    public OptionItemService(OptionItemRepository optionItemMapper) {
        this.optionItemMapper = optionItemMapper;
    }

    public List<OptionItemResponse> findAll() {
        List<OptionItem> optionList = optionItemMapper.findAll();

        return optionList.stream().map(OptionItem::toResponse)
                .collect(Collectors.toList());
    }

    public OptionItemResponse findById(Long id) {
        OptionItem option = optionItemMapper.findById(id);

        return option.toResponse();
    }

    public OptionItemResponse add(OptionItemRequest resource) {
        OptionItem option = resource.toEntity();
        optionItemMapper.insert(option);

        return option.toResponse();
    }

    public int update(Long id, OptionItemRequest resource) {

        OptionItem optionItem = optionItemMapper.findById(id);
        optionItem.updateInfo(resource);

        return optionItemMapper.updateOptionItem(id, optionItem);
    }

    public void deleteById(Long id) {
        optionItemMapper.deleteById(id);
    }
}
