package kr.fiveminutesmarket.option.repository;

import kr.fiveminutesmarket.option.domain.OptionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionItemRepository {

    int insert(@Param("optionItem") OptionItem optionItem);

    List<OptionItem> findAll();

    OptionItem findById(@Param("optionItemId") Long optionItemId);

    int updateOptionItem(@Param("optionItemId") Long optionItemId,
                     @Param("optionItem") OptionItem optionItem);

    void deleteById(@Param("optionItemId") Long optionItemId);

}
