package kr.fiveminutesmarket.order.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class KakaoPayApproved {

    private String aid;

    private String tid;

    private String cid;

    private String sid;

    @JsonProperty("partner_order_id")
    private String partnerOrderId;

    @JsonProperty("partner_user_id")
    private String partnerUserId;

    @JsonProperty("payment_method_type")
    private String paymentMethodType;

    @JsonProperty("amount")
    private PayedAmount payedAmount;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_code")
    private String itemCode;

    private Integer quantity;

    // 결제 준비 요청 시각
    @JsonProperty("created_at")
    private Date createdAt;

    // 결제 승인 시각
    @JsonProperty("approved_at")
    private Date approvedAt;

    private static class PayedAmount {
        @JsonProperty("total")
        private Integer total;

        @JsonProperty("tax_free")
        private Integer taxFree;

        @JsonProperty("vat")
        private Integer vat;

        @JsonProperty("point")
        private Integer point;

        @JsonProperty("discount")
        private Integer discount;
    }

    public KakaoPayApproved() {
    }

    public String getAid() {
        return aid;
    }

    public String getTid() {
        return tid;
    }

    public String getCid() {
        return cid;
    }

    public String getSid() {
        return sid;
    }

    public String getPartnerOrderId() {
        return partnerOrderId;
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public PayedAmount getPayedAmount() {
        return payedAmount;
    }
}
