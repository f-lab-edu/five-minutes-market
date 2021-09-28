package kr.fiveminutesmarket.order.repository;

import kr.fiveminutesmarket.order.domain.PaymentHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentHistoryRepository {
    void insertPaymentHistory(@Param("paymentHistory") PaymentHistory paymentHistory);

    PaymentHistory findByOrdersId(Long ordersId);
}
