package kr.fiveminutesmarket.order.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.order.domain.KakaoPayReady;
import kr.fiveminutesmarket.order.dto.OrdersByUserResponseDto;
import kr.fiveminutesmarket.order.payment.KakaopayPayment;
import kr.fiveminutesmarket.order.service.OrdersService;
import kr.fiveminutesmarket.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(KakaopayPayment.class);
    private final OrdersService ordersService;
    private final PaymentService paymentService;

    public OrdersController(OrdersService ordersService, PaymentService paymentService) {
        this.ordersService = ordersService;
        this.paymentService = paymentService;
    }

    @GetMapping("/user/{userId}/orders")
    public ResponseDto<List<OrdersByUserResponseDto>> getOrderListByUser(@PathVariable("userId") Long userId,
                                                                         @RequestParam("startIndex") int startIndex,
                                                                         @RequestParam("count") int count) {

        List<OrdersByUserResponseDto> ordersByUserResponseDtoList
                = ordersService.getOrderListWithUserId(userId, startIndex, count);

        return new ResponseDto<>(0, null, ordersByUserResponseDtoList);
    }

    @GetMapping("/orders/{orderId}/{paymentMethod}")
    public ResponseDto<?> paymentOrder(@PathVariable("orderId") Long orderId,
                                                   @PathVariable("paymentMethod") String paymentMethod) {

        return new ResponseDto<>(0, "", paymentService.payment(orderId, paymentMethod));
    }
}
