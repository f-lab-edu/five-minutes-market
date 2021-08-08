package kr.fiveminutesmarket.order.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.order.dto.OrdersByUserResponseDto;
import kr.fiveminutesmarket.order.service.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/user/{userId}/orders")
    public ResponseDto<List<OrdersByUserResponseDto>> getOrderListByUser(@PathVariable("userId") Long userId,
                                                                         @RequestParam("startIndex") int startIndex,
                                                                         @RequestParam("count") int count) {

        List<OrdersByUserResponseDto> ordersByUserResponseDtoList
                = ordersService.getOrderListWithUserId(userId, startIndex, count);

        return new ResponseDto<>(0, null, ordersByUserResponseDtoList);
    }
}
