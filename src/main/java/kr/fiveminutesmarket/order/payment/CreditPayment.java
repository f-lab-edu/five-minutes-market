package kr.fiveminutesmarket.order.payment;

import kr.fiveminutesmarket.order.domain.Orders;
import org.springframework.stereotype.Component;

@Component
public class CreditPayment implements Payment{

    @Override
    public String payment(Orders orders) {
        return "구매 완료!";
    }

    @Override
    public String approve(Orders orders, String token) {
        return "결제 승인 완료!";
    }
}
