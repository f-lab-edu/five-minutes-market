package kr.fiveminutesmarket.order.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fiveminutesmarket.common.exception.errors.JsonSerializeFailedException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class KakaoPayReady {

    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private String created_at;

    public KakaoPayReady() {
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getNext_redirect_app_url() {
        return next_redirect_app_url;
    }

    public void setNext_redirect_app_url(String next_redirect_app_url) {
        this.next_redirect_app_url = next_redirect_app_url;
    }

    public String getNext_redirect_mobile_url() {
        return next_redirect_mobile_url;
    }

    public void setNext_redirect_mobile_url(String next_redirect_mobile_url) {
        this.next_redirect_mobile_url = next_redirect_mobile_url;
    }

    public String getNext_redirect_pc_url() {
        return next_redirect_pc_url;
    }

    public void setNext_redirect_pc_url(String next_redirect_pc_url) {
        this.next_redirect_pc_url = next_redirect_pc_url;
    }

    public String getAndroid_app_scheme() {
        return android_app_scheme;
    }

    public void setAndroid_app_scheme(String android_app_scheme) {
        this.android_app_scheme = android_app_scheme;
    }

    public String getIos_app_scheme() {
        return ios_app_scheme;
    }

    public void setIos_app_scheme(String ios_app_scheme) {
        this.ios_app_scheme = ios_app_scheme;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
