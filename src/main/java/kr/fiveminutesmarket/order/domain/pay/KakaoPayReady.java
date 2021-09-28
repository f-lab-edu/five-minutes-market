package kr.fiveminutesmarket.order.domain.pay;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
