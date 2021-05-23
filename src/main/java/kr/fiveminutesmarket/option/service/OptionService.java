package kr.fiveminutesmarket.option.service;

import kr.fiveminutesmarket.option.domain.Option;
import kr.fiveminutesmarket.option.dto.OptionRequest;
import kr.fiveminutesmarket.option.dto.OptionResponse;
import kr.fiveminutesmarket.option.mapper.OptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OptionService {

    private final OptionMapper optionMapper;

    public OptionService(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    public List<OptionResponse> findAll() {
        List<Option> optionList = optionMapper.findAll();

        return optionList.stream().map(Option::toResponse)
                .collect(Collectors.toList());
    }

    public OptionResponse findById(Long id) {
        Option option = optionMapper.findByOptionId(id);

        return option.toResponse();
    }

    public OptionResponse add(OptionRequest resource) {
        Option option = resource.toEntity();
        optionMapper.insert(option);

        return option.toResponse();
    }

    public int update(Long id, OptionRequest resource) {

        Option option = optionMapper.findByOptionId(id);
        option.updateInfo(resource);

        return optionMapper.updateOption(id, option);
    }

}
