package kr.fiveminutesmarket.order.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.order.dto.OrderByUserResponseDto;
import kr.fiveminutesmarket.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/{userId}/order")
    public ResponseDto<List<OrderByUserResponseDto>> getOrderListByUser(@PathVariable("userId") Long userId,
                                                                        @RequestParam("startIndex") int startIndex,
                                                                        @RequestParam("count") int count) {

        List<OrderByUserResponseDto> orderByUserResponseDtoList
                = orderService.getOrderListWithUserId(userId, startIndex, count);

        return new ResponseDto<>(0, null, orderByUserResponseDtoList);
    }
}
