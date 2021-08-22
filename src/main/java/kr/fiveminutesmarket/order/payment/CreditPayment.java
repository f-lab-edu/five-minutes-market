package kr.fiveminutesmarket.order.payment;

import kr.fiveminutesmarket.order.domain.Orders;
import org.springframework.stereotype.Component;

@Component
public class CreditPayment implements Payment{

    @Override
    public String payment(Orders orderProduct) {
        return "구매 완료!";
    }
}
