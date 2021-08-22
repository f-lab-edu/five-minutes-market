package kr.fiveminutesmarket.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    @DisplayName("수량이 0 보다 적을 시 예외 발생")
    void amountInitializationThrowsExceptionTest(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Amount(value);
                }).withMessage("수량은 0보다 작을수 없습니다.");
    }
}