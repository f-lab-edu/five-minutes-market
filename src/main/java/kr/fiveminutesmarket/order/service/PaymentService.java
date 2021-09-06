package kr.fiveminutesmarket.order.service;

import kr.fiveminutesmarket.common.utils.RedisUtils;
import kr.fiveminutesmarket.order.domain.OrderProduct;
import kr.fiveminutesmarket.order.domain.Orders;
import kr.fiveminutesmarket.order.domain.PaymentHistory;
import kr.fiveminutesmarket.order.domain.PaymentMethod;
import kr.fiveminutesmarket.order.dto.OrderProductRequestDto;
import kr.fiveminutesmarket.order.dto.OrdersRequestDto;
import kr.fiveminutesmarket.order.exception.errors.InvalidPaymentMethodException;
import kr.fiveminutesmarket.order.payment.Payment;
import kr.fiveminutesmarket.order.repository.OrderProductRepository;
import kr.fiveminutesmarket.order.repository.OrdersRepository;
import kr.fiveminutesmarket.order.repository.PaymentHistoryRepository;
import kr.fiveminutesmarket.user.domain.User;
import kr.fiveminutesmarket.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final OrdersRepository ordersRepository;
    private final OrderProductRepository orderProductRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final List<Payment> payments;

    public PaymentService(OrdersRepository ordersRepository,
                          OrderProductRepository orderProductRepository,
                          PaymentHistoryRepository paymentHistoryRepository,
                          UserRepository userRepository,
                          RedisTemplate<String, Object> redisTemplate,
                          List<Payment> payments) {
        this.ordersRepository = ordersRepository;
        this.orderProductRepository = orderProductRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.payments = payments;
    }

    @Transactional
    public String readyPayment(String paymentMethod, String email, OrdersRequestDto orderRequestDto) {
        Payment payment = selectProperPayment(paymentMethod);
        User user = userRepository.findByEmail(email);

        Orders orders = toOrdersEntity(user.getUserId(), orderRequestDto);
        // save orders
        ordersRepository.insert(orders);

        List<OrderProduct> orderProducts = orderRequestDto.getProductList()
                .stream()
                .map(orderProductRequestDto
                        -> toOrderProductEntity(orderProductRequestDto, orders.getOrderId()))
                .collect(Collectors.toList());

        // save orderProducts in orders
        orderProducts.forEach(orderProductRepository::insert);

        orders.setOrderProducts(orderProducts);
        return payment.payment(orders);
    }

    @Transactional
    public String approvePayment(Long orderId, String pgToken, String paymentMethod) {
        Payment payment = selectProperPayment(paymentMethod);

        Orders orders = ordersRepository.findByOrderId(orderId);

        String paymentApprovedLog = payment.approve(orders, pgToken);

        String redisKey = RedisUtils.createKeyWithPrefix(paymentMethod, String.valueOf(orderId));
        String tid = (String) redisTemplate.opsForValue().get(redisKey);

        // 결제 history 생성
        PaymentHistory paymentHistory = toPaymentHistoryEntity(tid, orderId, true, paymentApprovedLog);
        paymentHistoryRepository.insertPaymentHistory(paymentHistory);
        return paymentApprovedLog;
    }

    public String failPayment(Long orderId, String paymentMethod) {
        Payment payment = selectProperPayment(paymentMethod);

        Orders orders = ordersRepository.findByOrderId(orderId);
        logger.info("################## payment failed order ##################### " + orders.toString());
        String paymentFailedLog = payment.fail(orders);
        logger.info("################## payment failed log ##################### " + paymentFailedLog);

        String redisKey = RedisUtils.createKeyWithPrefix(paymentMethod, String.valueOf(orderId));
        String tid = (String) redisTemplate.opsForValue().get(redisKey);

        // 결제 history 생성
        PaymentHistory paymentHistory = toPaymentHistoryEntity(tid, orderId, false, paymentFailedLog);
        paymentHistoryRepository.insertPaymentHistory(paymentHistory);

        return paymentFailedLog;
    }

    public String cancelPayment(Long orderId, String paymentMethod) {
        Payment payment = selectProperPayment(paymentMethod);

        Orders orders = ordersRepository.findByOrderId(orderId);
        return null;
    }

    private Payment selectProperPayment(String paymentMethod) {
        return payments.stream()
                .filter(clazz -> clazz.getClass() == PaymentMethod.of(paymentMethod).getPayment())
                .findFirst()
                .orElseThrow(() -> new InvalidPaymentMethodException("잘못된 결제 수단입니다."));
    }

    private Orders toOrdersEntity(Long userId, OrdersRequestDto orderRequestDto) {
        LocalDateTime now = LocalDateTime.now();

        return new Orders(
                orderRequestDto.getTotalPrice(),
                orderRequestDto.getAddress(),
                orderRequestDto.getMessage(),
                now,
                userId
                );
    }

    private OrderProduct toOrderProductEntity(OrderProductRequestDto orderProductRequestDto, Long orderId) {
        return new OrderProduct(orderProductRequestDto.getProductName(),
                orderProductRequestDto.getAmount(),
                orderProductRequestDto.getPrice(),
                orderId);
    }

    private PaymentHistory toPaymentHistoryEntity(String tid, Long ordersId, Boolean success, String paymentLog) {
        return new PaymentHistory(tid, ordersId, success, paymentLog);
    }
}
