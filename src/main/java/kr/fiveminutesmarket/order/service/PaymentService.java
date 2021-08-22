package kr.fiveminutesmarket.order.service;

import kr.fiveminutesmarket.order.domain.KakaoPayReady;
import kr.fiveminutesmarket.order.domain.OrderProduct;
import kr.fiveminutesmarket.order.domain.Orders;
import kr.fiveminutesmarket.order.domain.PaymentMethod;
import kr.fiveminutesmarket.order.exception.errors.InvalidPaymentMethodException;
import kr.fiveminutesmarket.order.payment.KakaopayPayment;
import kr.fiveminutesmarket.order.payment.Payment;
import kr.fiveminutesmarket.order.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Service
public class PaymentService {

    private final OrdersRepository ordersRepository;
    private final List<Payment> payments;

    public PaymentService(OrdersRepository ordersRepository, List<Payment> payments) {
        this.ordersRepository = ordersRepository;
        this.payments = payments;
    }

    public String payment(Long orderId, String paymentMethod) {
        Payment payment = payments.stream()
                .filter(clazz -> clazz.getClass() == PaymentMethod.of(paymentMethod).getPayment())
                .findFirst()
                .orElseThrow(() -> new InvalidPaymentMethodException("잘못된 결제 수단입니다."));
        Orders orders = ordersRepository.findByOrderId(orderId);

        return payment.payment(orders);
    }
}
