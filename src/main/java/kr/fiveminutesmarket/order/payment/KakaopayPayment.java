package kr.fiveminutesmarket.order.payment;

import kr.fiveminutesmarket.order.domain.KakaoPayReady;
import kr.fiveminutesmarket.order.domain.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class KakaopayPayment implements Payment{

    private static final Logger logger = LoggerFactory.getLogger(KakaopayPayment.class);
    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReady kakaoPayReady;

    @Value("${kakao.admin.token}")
    private String token;

    @Value("${kakao.redirect_domain}")
    private String domain;

    public String payment(Orders orders) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + token);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", String.valueOf(orders.getOrderId()));
        params.add("partner_user_id", String.valueOf(orders.getUserId()));
        params.add("item_name", orders.getOrderProducts().get(0).getProductName());
        params.add("quantity", String.valueOf(orders.getOrderProducts().get(0).getAmount()));
        params.add("total_amount", String.valueOf(orders.getTotalPrice()));
        params.add("tax_free_amount", String.valueOf(orders.getTotalPrice() / 10));
        params.add("approval_url", domain + "/orders/payments/kakaopay/success");
        params.add("cancel_url", domain + "/orders/payments/kakaopay/cancel");
        params.add("fail_url", domain + "/orders/payments/kakaopay/fail");


        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReady.class);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }

        return KakaoPayReady.toJson(kakaoPayReady);
    }
}
