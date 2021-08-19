package kr.fiveminutesmarket.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderStatusTest {
    @Test
    @DisplayName("전환 가능한 주문상태가 포함이된 경우 테스트")
    public void validateWhenContainsTrue() {
        // PAYMENT_WAITING(결제대기) -> PAYMENT_COMPLETED(결제완료)
        OrderStatus targetStatus = OrderStatus.PAYMENT_COMPLETED;
        OrderStatus currentStatus = OrderStatus.PAYMENT_WAITING;

        currentStatus.validate(targetStatus.getStatus());
    }

    @Test
    @DisplayName("전환 가능한 주문상태가 없는 경우 예외 처리 테스트")
    public void validateWhenContainsFalse() {
        // ARRIVAL(배송완료) -> PAYMENT_WAITING(결제대기)
        OrderStatus targetStatus = OrderStatus.PAYMENT_WAITING;
        OrderStatus currentStatus = OrderStatus.ARRIVAL;

        Throwable thrown = catchThrowable(() ->
                currentStatus.validate(targetStatus.getStatus())
        );

        assertThat(thrown)
                .isInstanceOf(OrderStatus.OrderStatusNotPossibleConvertException.class)
                .hasMessageContaining("\"" +
                        currentStatus.getStatus() +
                        "\" 상태에서 \"" +
                        targetStatus.getStatus() +
                        "\" 상태로 전환하는 것은 불가능합니다."
                );
    }
}