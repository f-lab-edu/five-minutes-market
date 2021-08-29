package kr.fiveminutesmarket.order.service;

import kr.fiveminutesmarket.common.utils.RedisUtils;
import kr.fiveminutesmarket.order.domain.Orders;
import kr.fiveminutesmarket.order.domain.PaymentHistory;
import kr.fiveminutesmarket.order.domain.PaymentMethod;
import kr.fiveminutesmarket.order.exception.errors.InvalidPaymentMethodException;
import kr.fiveminutesmarket.order.payment.KakaopayPayment;
import kr.fiveminutesmarket.order.payment.Payment;
import kr.fiveminutesmarket.order.repository.OrdersRepository;
import kr.fiveminutesmarket.order.repository.PaymentHistoryRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final OrdersRepository ordersRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final List<Payment> payments;

    public PaymentService(OrdersRepository ordersRepository,
                          PaymentHistoryRepository paymentHistoryRepository,
                          RedisTemplate<String, Object> redisTemplate,
                          List<Payment> payments) {
        this.ordersRepository = ordersRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.redisTemplate = redisTemplate;
        this.payments = payments;
    }

    public String readyPayment(Long orderId, String paymentMethod) {
        Payment payment = payments.stream()
                .filter(clazz -> clazz.getClass() == PaymentMethod.of(paymentMethod).getPayment())
                .findFirst()
                .orElseThrow(() -> new InvalidPaymentMethodException("잘못된 결제 수단입니다."));
        Orders orders = ordersRepository.findByOrderId(orderId);

        return payment.payment(orders);
    }

    public String approvePayment(Long orderId, String pgToken, String paymentMethod) {
        Payment payment = payments.stream()
                .filter(clazz -> clazz.getClass() == PaymentMethod.of(paymentMethod).getPayment())
                .findFirst()
                .orElseThrow(() -> new InvalidPaymentMethodException("잘못된 결제 수단입니다."));

        Orders orders = ordersRepository.findByOrderId(orderId);

        String paymentApprovedLog = payment.approve(orders, pgToken);

        Long ordersId = orders.getOrderId();
        String redisKey = RedisUtils.createKeyWithPrefix(KakaopayPayment.PLATFORM, String.valueOf(orderId));
        String tid = (String) redisTemplate.opsForValue().get(redisKey);

        // 결제 history 생성
        PaymentHistory paymentHistory = toPaymentHistoryEntity(tid, ordersId, true, paymentApprovedLog);
        paymentHistoryRepository.insertPaymentHistory(paymentHistory);
        return paymentApprovedLog;
    }

    public PaymentHistory toPaymentHistoryEntity(String tid, Long ordersId, Boolean success, String paymentLog) {

        return new PaymentHistory(tid, ordersId, success, paymentLog);
    }
}
