package kr.fiveminutesmarket.order.payment;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;

class KakaopayPaymentTest {

    static class TestDate {
        LocalDateTime date;

        public TestDate(LocalDateTime date) {
            this.date = date;
        }
    }

    KakaopayPayment kakaoPayment = new KakaopayPayment(new RedisTemplate<>());

    @Test
    void Test() throws URISyntaxException {
        kakaoPayment.payment(null);
    }

    @Test
    void makeOrderId() {
        System.out.println(new Date().getTime());
    }
}