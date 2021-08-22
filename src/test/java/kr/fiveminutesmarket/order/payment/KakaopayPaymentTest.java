package kr.fiveminutesmarket.order.payment;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class KakaopayPaymentTest {

    KakaopayPayment kakaoPayment = new KakaopayPayment();

    @Test
    void Test() throws URISyntaxException {
        kakaoPayment.payment(null);
    }
}