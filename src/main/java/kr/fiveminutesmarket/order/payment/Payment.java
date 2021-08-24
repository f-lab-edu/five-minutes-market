package kr.fiveminutesmarket.order.payment;

import kr.fiveminutesmarket.order.domain.Orders;

public interface Payment {

    String payment(Orders orders);

    String approve(Orders orders, String token);
}
