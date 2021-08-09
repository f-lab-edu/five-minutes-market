package kr.fiveminutesmarket.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    @DisplayName("")
    void priceInitializationThrowsExceptionTest(int value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Price(value);
                }).withMessage("가격은 0보다 작을수 없습니다.");
    }
}