package kr.fiveminutesmarket.option.mapper;

import kr.fiveminutesmarket.option.domain.Option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptionMapper {

    int insert(@Param("option") Option option);

    List<Option> findAll();

    Option findByOptionId(@Param("optionId") Long optionId);

    int updateOption(@Param("optionId") Long optionId,
                     @Param("option") Option option);

}
