package kr.fiveminutesmarket.option.mapper;

import kr.fiveminutesmarket.option.domain.Option;
import kr.fiveminutesmarket.option.domain.OptionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionItemMapper {

    int insert(@Param("optionItem") OptionItem optionItem);

    List<OptionItem> findAll();

    OptionItem findByOptionItemId(@Param("optionItemId") Long optionItemId);

    int updateOptionItem(@Param("optionItemId") Long optionItemId,
                     @Param("optionItem") OptionItem optionItem);
}
