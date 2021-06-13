package kr.fiveminutesmarket.common.jwt;

import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilsTest {

    private JwtTokenUtils jwtTokenUtils;

    @BeforeEach
    void setUp() {
        jwtTokenUtils = new JwtTokenUtils();
    }

    @Test
    @DisplayName("토큰을 생성했을 때 isValidToken() 메서드 예외 검증")
    void isValidTokenTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            jwtTokenUtils.isValidToken(null);
        });

        assertThat(exception.getClass()).isEqualTo(IllegalArgumentException.class);

        exception = assertThrows(JwtException.class, () -> {
            jwtTokenUtils.isValidToken("test");
        });

        assertThat(exception.getClass()).isEqualTo(JwtException.class);
    }

}