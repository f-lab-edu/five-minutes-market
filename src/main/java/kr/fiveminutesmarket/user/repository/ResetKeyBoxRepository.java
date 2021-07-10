package kr.fiveminutesmarket.user.repository;

import kr.fiveminutesmarket.user.event.ResetKeyBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResetKeyBoxRepository {
    void insertResetKeyBox(@Param("resetKeyBox") ResetKeyBox resetKeyBox);

    List<ResetKeyBox> findAll();

    void deleteByBoxId(Long resetKeyBoxId);
}
