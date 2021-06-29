package kr.fiveminutesmarket.outbox.repository;

import kr.fiveminutesmarket.outbox.domain.OutBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OutBoxRepository {

    void insertOutBox(@Param("outBox") OutBox outBox);

    List<OutBox> findAll();

    void deleteAllByOutBoxId(List<Long> outBoxIdList);
}
