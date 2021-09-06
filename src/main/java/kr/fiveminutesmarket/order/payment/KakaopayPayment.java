package kr.fiveminutesmarket.order.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fiveminutesmarket.common.exception.errors.JsonSerializeFailedException;
import kr.fiveminutesmarket.common.utils.RedisUtils;
import kr.fiveminutesmarket.order.domain.Orders;
import kr.fiveminutesmarket.order.domain.pay.KakaoPayApproved;
import kr.fiveminutesmarket.order.domain.pay.KakaoPayFailed;
import kr.fiveminutesmarket.order.domain.pay.KakaoPayReady;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Component
public class KakaopayPayment implements Payment {
    private static final String HOST = "https://kapi.kakao.com";
    private static final String CID = "TC0ONETIME";
    public static final String PLATFORM = "kakaopay";

    @Value("${kakao.admin.token}")
    private String token;

    @Value("${kakao.redirect_domain}")
    private String domain;

    private final RedisTemplate<String, Object> redisTemplate;

    public KakaopayPayment(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 카카오페이 결제 준비
    @Override
    public String payment(Orders orders) {
        HttpHeaders headers = makeCommonHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        // redirect url: [domain]/orders/{orderId}/payments/kakaopay/...
        String redirectUrl = domain
                + "/orders/"
                + orders.getOrderId()
                + "/payments/"
                + PLATFORM;

        params.add("cid", CID);
        params.add("partner_order_id", String.valueOf(orders.getOrderId()));
        params.add("partner_user_id", String.valueOf(orders.getUserId()));
        params.add("item_name", orders.getOrderProducts().get(0).getProductName());
        params.add("quantity", String.valueOf(orders.getOrderProducts().get(0).getAmount()));
        params.add("total_amount", String.valueOf(orders.getTotalPrice()));
        params.add("tax_free_amount", String.valueOf(orders.getTotalPrice() / 10));
        params.add("approval_url", redirectUrl + "/success");
        params.add("cancel_url", redirectUrl + "/cancel");
        params.add("fail_url", redirectUrl + "/fail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(HOST)
                .path("/v1/payment/ready")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        KakaoPayReady kakaoPayReady = restTemplate.postForObject(uri, body, KakaoPayReady.class);

        if (kakaoPayReady != null) {
            String redisKey = RedisUtils.createKeyWithPrefix(PLATFORM, String.valueOf(orders.getOrderId()));
            redisTemplate.opsForValue().set(redisKey, kakaoPayReady.getTid());
            redisTemplate.expire(redisKey, 1800, TimeUnit.SECONDS);  // 30분 만료시간
        }

        return toJson(kakaoPayReady);
    }

    // 카카오페이 결제 승인
    @Override
    public String approve(Orders orders, String token) {
        HttpHeaders headers = makeCommonHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        String redisKey = RedisUtils.createKeyWithPrefix(PLATFORM, String.valueOf(orders.getOrderId()));
        String tid = (String) redisTemplate.opsForValue().get(redisKey);

        params.add("cid", CID);
        params.add("tid", tid);
        params.add("partner_order_id", String.valueOf(orders.getOrderId()));
        params.add("partner_user_id", String.valueOf(orders.getUserId()));
        params.add("pg_token", token);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        URI uri = UriComponentsBuilder
                .fromUriString(HOST)
                .path("/v1/payment/approve")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        KakaoPayApproved kakaoPayApproved = restTemplate.postForObject(uri, body, KakaoPayApproved.class);

        return toJson(kakaoPayApproved);
    }

    @Override
    public String fail(Orders orders) {
        String redisKey = RedisUtils.createKeyWithPrefix(PLATFORM, String.valueOf(orders.getOrderId()));
        String tid = (String) redisTemplate.opsForValue().get(redisKey);

        KakaoPayFailed kakaoPayFailed = new KakaoPayFailed(
                tid,
                orders.getOrderId(),
                orders.getUserId(),
                orders.getTotalPrice());

        return toJson(kakaoPayFailed);
    }

    @Override
    public String cancel(Orders orders) {
        return "";
    }

    // 공통 Header 구성
    private HttpHeaders makeCommonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + token);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return headers;
    }

    private String toJson(Object kakaoPayResponse) {
        ObjectMapper mapper = new ObjectMapper();

        String json = "";
        try {
            json = mapper.writeValueAsString(mapper.convertValue(kakaoPayResponse, kakaoPayResponse.getClass()));
        } catch (JsonProcessingException e) {
            throw new JsonSerializeFailedException();
        }

        return json;
    }
}
