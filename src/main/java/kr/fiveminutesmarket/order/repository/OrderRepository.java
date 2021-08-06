package kr.fiveminutesmarket.order.repository;

import kr.fiveminutesmarket.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderRepository {
    List<Order> findAllByUserIdOrderByCreatedDate(Long userId, int startIndex, int itemCount);
}
