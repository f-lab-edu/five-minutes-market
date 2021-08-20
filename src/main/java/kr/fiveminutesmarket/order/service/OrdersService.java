package kr.fiveminutesmarket.order.service;

import kr.fiveminutesmarket.order.domain.OrderProduct;
import kr.fiveminutesmarket.order.domain.Orders;
import kr.fiveminutesmarket.order.dto.OrderProductResponseDto;
import kr.fiveminutesmarket.order.dto.OrdersByUserResponseDto;
import kr.fiveminutesmarket.order.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrdersByUserResponseDto> getOrderListWithUserId(Long userId, int startIndex, int itemCount) {
        List<Orders> orderList = ordersRepository.findAllByUserIdOrderByCreatedDate(userId, startIndex, itemCount);

        return orderList.stream()
                .map(this::toResponseByUser)
                .collect(Collectors.toList());
    }

    public OrdersByUserResponseDto toResponseByUser(Orders orders) {
        return new OrdersByUserResponseDto(
                orders.getOrderId(),
                orders.getTotalPrice(),
                orders.getAddress(),
                orders.getOrderStatus(),
                orders.getMessage(),
                orders.getCreatedDate(),
                orders.getUpdatedDate(),
                orders.getUserId(),
                orders.getOrderProducts().stream()
                        .map(this::toOrderProductResponse)
                        .collect(Collectors.toList())
        );
    }

    public OrderProductResponseDto toOrderProductResponse(OrderProduct orderProduct) {
        return new OrderProductResponseDto(
                orderProduct.getOrderProductId(),
                orderProduct.getProductName(),
                orderProduct.getAmount(),
                orderProduct.getPrice()
        );
    }
}
