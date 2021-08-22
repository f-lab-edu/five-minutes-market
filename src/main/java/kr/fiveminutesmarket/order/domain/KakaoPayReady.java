package kr.fiveminutesmarket.order.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fiveminutesmarket.common.exception.errors.JsonSerializeFailedException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class KakaoPayReady {

    private String tid;

    @JsonProperty("next_redirect_app_url")
    private String nextRedirectAppUrl;

    @JsonProperty("next_redirect_mobile_url")
    private String nextRedirectMobileUrl;

    @JsonProperty("next_redirect_pc_url")
    private String nextRedirectPcUrl;

    @JsonProperty("android_app_scheme")
    private String androidAppScheme;

    @JsonProperty("ios_app_scheme")
    private String iosAppScheme;

    @JsonProperty("created_at")
    private String createdAt;

    public KakaoPayReady() {
    }

    public String getTid() {
        return tid;
    }

    public String getNextRedirectAppUrl() {
        return nextRedirectAppUrl;
    }

    public String getNextRedirectMobileUrl() {
        return nextRedirectMobileUrl;
    }

    public String getNextRedirectPcUrl() {
        return nextRedirectPcUrl;
    }

    public String getAndroidAppScheme() {
        return androidAppScheme;
    }

    public String getIosAppScheme() {
        return iosAppScheme;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static KakaoPayReady of(String json) {
        ObjectMapper mapper = new ObjectMapper();

        if (json == null || !"".equals(json)) {
            try {
                return mapper.readValue(json, KakaoPayReady.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String toJson(KakaoPayReady userSession) {
        ObjectMapper mapper = new ObjectMapper();

        String json = "";
        try {
            json = mapper.writeValueAsString(mapper.convertValue(userSession, KakaoPayReady.class));
        } catch (JsonProcessingException e) {
            throw new JsonSerializeFailedException();
        }

        return json;
    }
}
