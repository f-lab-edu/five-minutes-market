package kr.fiveminutesmarket.order.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.order.dto.OrdersByUserResponseDto;
import kr.fiveminutesmarket.order.service.OrdersService;
import kr.fiveminutesmarket.order.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

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

    @GetMapping("/orders/{orderId}/{paymentMethod}")
    public ResponseDto<String> paymentOrder(@PathVariable("orderId") Long orderId,
                                            @PathVariable("paymentMethod") String paymentMethod) {

        return new ResponseDto<>(0, "결제대기", paymentService.readyPayment(orderId, paymentMethod));
    }
}
