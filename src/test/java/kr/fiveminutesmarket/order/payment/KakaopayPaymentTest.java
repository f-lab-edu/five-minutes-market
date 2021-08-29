package kr.fiveminutesmarket.order.payment;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class KakaopayPaymentTest {

    KakaopayPayment kakaoPayment = new KakaopayPayment(new RedisTemplate<>());

    @Test
    void Test() throws URISyntaxException {
        kakaoPayment.payment(null);
    }
}