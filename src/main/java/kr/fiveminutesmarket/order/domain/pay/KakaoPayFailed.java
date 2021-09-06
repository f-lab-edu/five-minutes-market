package kr.fiveminutesmarket.order.domain.pay;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class KakaoPayFailed {

    private String tid;

    private Long orderId;

    private Long userId;

    private Integer total;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime failedAt;

    public KakaoPayFailed() {
    }

    public KakaoPayFailed(String tid, Long orderId, Long userId, Integer total) {
        this.tid = tid;
        this.orderId = orderId;
        this.userId = userId;
        this.total = total;
        this.failedAt = LocalDateTime.now();
    }

    public String getTid() {
        return tid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getTotal() {
        return total;
    }

    public LocalDateTime getFailedAt() {
        return failedAt;
    }
}
