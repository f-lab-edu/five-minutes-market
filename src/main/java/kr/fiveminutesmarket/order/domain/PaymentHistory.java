package kr.fiveminutesmarket.order.domain;

public class PaymentHistory {
    private Long paymentHistoryId;
    // 결제 ID
    private String tid;
    // 주문 ID
    private Long ordersId;

    private Boolean success;

    private String paymentLog;

    public PaymentHistory() {
    }

    public PaymentHistory(String tid, Long ordersId, Boolean success, String paymentLog) {
        this.tid = tid;
        this.ordersId = ordersId;
        this.success = success;
        this.paymentLog = paymentLog;
    }

    public Long getPaymentHistoryId() {
        return paymentHistoryId;
    }

    public String getTid() {
        return tid;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public Boolean isSuccess() {
        return success;
    }

    public String getPaymentLog() {
        return paymentLog;
    }
}
