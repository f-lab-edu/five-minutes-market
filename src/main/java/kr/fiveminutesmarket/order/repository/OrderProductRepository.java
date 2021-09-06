package kr.fiveminutesmarket.order.repository;

import kr.fiveminutesmarket.order.domain.OrderProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderProductRepository {
    void insert(@Param("orderProduct") OrderProduct orderProduct);
}
