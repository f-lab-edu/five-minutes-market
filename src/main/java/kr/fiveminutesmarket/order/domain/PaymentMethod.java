package kr.fiveminutesmarket.order.domain;

import kr.fiveminutesmarket.order.exception.errors.InvalidPaymentMethodException;
import kr.fiveminutesmarket.order.payment.CreditPayment;
import kr.fiveminutesmarket.order.payment.KakaopayPayment;
import kr.fiveminutesmarket.order.payment.Payment;

import java.util.Arrays;

public enum PaymentMethod {
    CREDIT("credit", CreditPayment.class),
    KAKAOPAY("kakaopay", KakaopayPayment.class);

    private final String channel;
    private final Class payment;

    PaymentMethod(String channel, Class<? extends Payment> payment) {
        this.channel = channel;
        this.payment = payment;
    }

    public static PaymentMethod of(String channel) {
        return Arrays.stream(PaymentMethod.values())
                    .filter(ch -> ch.channel.equals(channel))
                    .findFirst()
                    .orElseThrow(() -> new InvalidPaymentMethodException("잘못된 결제 수단입니다."));
    }

    public String getValue() {
        return channel;
    }

    public Class<? extends Payment> getPayment() {
        return payment;
    }
}
