package kr.fiveminutesmarket.order.service;

import kr.fiveminutesmarket.order.domain.*;
import kr.fiveminutesmarket.order.dto.OrdersByUserResponseDto;
import kr.fiveminutesmarket.order.dto.OrderProductResponseDto;
import kr.fiveminutesmarket.order.repository.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class OrderServiceTest {

    @InjectMocks
    private OrdersService orderService;

    @Mock
    private OrdersRepository orderRepository;

    private List<Orders> orders;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        makeMockOrderRepository();
    }

    private void makeMockOrderRepository() {
        orders = new ArrayList<>();

        List<OrderProduct> orderProducts1 = new ArrayList<>();
        List<OrderProduct> orderProducts2 = new ArrayList<>();

        OrderProduct orderProduct1 = new OrderProduct(20L, "상추", 10, 3_000, 1L);
        OrderProduct orderProduct2 = new OrderProduct(21L, "가지", 15, 2_500, 1L);
        OrderProduct orderProduct3 = new OrderProduct(22L, "수박", 2, 10_000, 2L);
        OrderProduct orderProduct4 = new OrderProduct(23L, "참외", 10, 5_000, 2L);

        orderProducts1.add(orderProduct1);
        orderProducts1.add(orderProduct2);
        orderProducts2.add(orderProduct3);
        orderProducts2.add(orderProduct4);

        Orders order1 = new Orders(
                1L,
                13_000,
                "test address 1",
                OrderStatus.ARRANGED,
                "test message 1",
                LocalDateTime.of(2021, 8, 1, 11, 10),
                LocalDateTime.of(2021, 8, 1, 15, 20),
                1000L,
                orderProducts1);

        Orders order2 = new Orders(
                2L,
                92_300,
                "test address 2",
                OrderStatus.DELIVERY,
                "test message 2",
                LocalDateTime.of(2021, 7, 29, 14, 20),
                LocalDateTime.of(2021, 7, 31, 17, 50),
                1000L,
                orderProducts2);

        orders.add(order1);
        orders.add(order2);

        given(orderRepository.findAllByUserIdOrderByCreatedDate(1000L, 0, 2)).willReturn(orders);
    }

    @Test
    @DisplayName("구매자 ID 기준 주문리스트 조회 테스트")
    public void getOrderListWithUserId() {
        List<OrdersByUserResponseDto> dtoList = orderService.getOrderListWithUserId(1000L, 0, 2);

        compareOrderDtoListWithGivenOrder(dtoList);
    }

    private void compareOrderDtoListWithGivenOrder(List<OrdersByUserResponseDto> dtoList) {
        for (int i = 0; i < dtoList.size(); i++) {
            assertThat(dtoList.get(i).getOrderId()).isEqualTo(orders.get(i).getOrderId());
            assertThat(dtoList.get(i).getTotalPrice()).isEqualTo(orders.get(i).getTotalPrice());
            assertThat(dtoList.get(i).getOrderStatus()).isEqualTo(orders.get(i).getOrderStatus());
            assertThat(dtoList.get(i).getUserId()).isEqualTo(orders.get(i).getUserId());

            compareOrderProductDtoListListWithGivenOrderProduct(
                    dtoList.get(i).getOrderProducts(),
                    orders.get(i).getOrderProducts());
        }
    }

    private void compareOrderProductDtoListListWithGivenOrderProduct(List<OrderProductResponseDto> dtoList,
                                                                     List<OrderProduct> orderProducts) {
        for (int i = 0; i < dtoList.size(); i++) {
            assertThat(dtoList.get(i).getOrderProductId()).isEqualTo(orderProducts.get(i).getOrderProductId());
            assertThat(dtoList.get(i).getProductName()).isEqualTo(orderProducts.get(i).getProductName());
            assertThat(dtoList.get(i).getAmount()).isEqualTo(orderProducts.get(i).getAmount());
            assertThat(dtoList.get(i).getPrice()).isEqualTo(orderProducts.get(i).getPrice());
        }
    }
}