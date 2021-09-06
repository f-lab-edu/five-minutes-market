package kr.fiveminutesmarket.order.controller;

import kr.fiveminutesmarket.common.annotation.LoginUser;
import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.common.dto.UserSessionDto;
import kr.fiveminutesmarket.order.dto.OrdersRequestDto;
import kr.fiveminutesmarket.order.dto.OrdersByUserResponseDto;
import kr.fiveminutesmarket.order.service.OrdersService;
import kr.fiveminutesmarket.order.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {
    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
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

    @GetMapping("/orders/{orderId}/payments/{paymentMethod}/success")
    public ResponseDto<String> approvePayment(@PathVariable("orderId") Long orderId,
                                              @PathVariable("paymentMethod") String paymentMethod,
                                              @RequestParam("pg_token") String pgToken) {

        return new ResponseDto<>(0, "결제승인완료", paymentService.approvePayment(orderId, pgToken, paymentMethod));
    }

    @GetMapping("/orders/{orderId}/payments/{paymentMethod}/fail")
    public ResponseDto<String> failedPayment(@PathVariable("orderId") Long orderId,
                                             @PathVariable("paymentMethod") String paymentMethod) {

        logger.info("################## payment failed #####################");
        return new ResponseDto<>(0, "결제실패", paymentService.failPayment(orderId, paymentMethod));
    }

    @GetMapping("/orders/{orderId}/payments/{paymentMethod}/cancel")
    public ResponseDto<String> cancelOrder(@PathVariable("orderId") Long orderId,
                                           @PathVariable("paymentMethod") String paymentMethod) {
        return new ResponseDto<>(0, "결제취소");
    }

    @GetMapping("/orders/{paymentMethod}")
    public ResponseDto<String> paymentOrder(@PathVariable("paymentMethod") String paymentMethod,
                                            @LoginUser UserSessionDto userSessionDto,
                                            @RequestBody OrdersRequestDto orderRequestDto) {

        return new ResponseDto<>(
                0,
                "결제대기",
                paymentService.readyPayment(paymentMethod, userSessionDto.getEmail(), orderRequestDto));
    }
}
