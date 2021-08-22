package kr.fiveminutesmarket.order.repository;

import kr.fiveminutesmarket.order.domain.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersRepository {
    List<Orders> findAllByUserIdOrderByCreatedDate(Long userId, int startIndex, int itemCount);

    Orders findByOrderId(Long orderId);
}
