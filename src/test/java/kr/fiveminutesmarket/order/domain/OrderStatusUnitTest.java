package kr.fiveminutesmarket.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderStatusUnitTest {

    @Test
    @DisplayName("주문상태 최초 생성 테스트")
    public void initialize() {
        OrderStatusUnit orderStatusUnit = OrderStatusUnit.initialize();
        assertThat(orderStatusUnit.getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    @DisplayName("주문 다음단계 설정 테스트")
    public void nextStep() {
        OrderStatusUnit expectedWaiting = OrderStatusUnit.initialize();
        OrderStatusUnit expectedDelivery = OrderStatusUnit.initialize();

        expectedWaiting.nextStep();     // 1단계

        expectedDelivery.nextStep();    // 2단계
        expectedDelivery.nextStep();

        assertThat(expectedWaiting.getStatus()).isEqualTo(OrderStatus.WAITING);
        assertThat(expectedDelivery.getStatus()).isEqualTo(OrderStatus.DELIVERY);
    }

    @Test
    @DisplayName("주문 취소 설정 테스트")
    public void cancel() {
        OrderStatusUnit expectedCancel = OrderStatusUnit.initialize();
        expectedCancel.cancel();

        assertThat(expectedCancel.getStatus()).isEqualTo(OrderStatus.CANCELED);
    }

}