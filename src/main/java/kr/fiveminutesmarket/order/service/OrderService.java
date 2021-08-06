package kr.fiveminutesmarket.order.service;

import kr.fiveminutesmarket.order.domain.Order;
import kr.fiveminutesmarket.order.domain.OrderProduct;
import kr.fiveminutesmarket.order.dto.OrderByUserResponseDto;
import kr.fiveminutesmarket.order.dto.OrderProductResponseDto;
import kr.fiveminutesmarket.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderByUserResponseDto> getOrderListWithUserId(Long userId, int startIndex, int itemCount) {
        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedDate(userId, startIndex, itemCount);

        return orderList.stream()
                .map(this::toResponseByUser)
                .collect(Collectors.toList());
    }

    public OrderByUserResponseDto toResponseByUser(Order order) {
        return new OrderByUserResponseDto(
                order.getOrderId(),
                order.getTotalPrice(),
                order.getAddress(),
                order.getPayment(),
                order.getOrderStatus(),
                order.getMessage(),
                order.getCreatedDate(),
                order.getUpdatedDate(),
                order.getUserId(),
                order.getOrderProducts().stream()
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
