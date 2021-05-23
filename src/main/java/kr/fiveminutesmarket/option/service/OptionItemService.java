package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.OptionItem;
import kr.fiveminutesmarket.option.dto.OptionItemRequest;
import kr.fiveminutesmarket.option.dto.OptionItemResponse;
import kr.fiveminutesmarket.option.mapper.OptionItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OptionItemService {

    private final OptionItemMapper optionItemMapper;

    public OptionItemService(OptionItemMapper optionItemMapper) {
        this.optionItemMapper = optionItemMapper;
    }

    public List<OptionItemResponse> findAll() {
        List<OptionItem> optionList = optionItemMapper.findAll();

        return optionList.stream().map(OptionItem::toResponse)
                .collect(Collectors.toList());
    }

    public OptionItemResponse findById(Long id) {
        OptionItem option = optionItemMapper.findByOptionItemId(id);

        return option.toResponse();
    }

    public OptionItemResponse add(OptionItemRequest resource) {
        OptionItem option = resource.toEntity();
        optionItemMapper.insert(option);

        return option.toResponse();
    }

    public int update(Long id, OptionItemRequest resource) {

        OptionItem optionItem = optionItemMapper.findByOptionItemId(id);
        optionItem.updateInfo(resource);

        return optionItemMapper.updateOptionItem(id, optionItem);
    }
}
