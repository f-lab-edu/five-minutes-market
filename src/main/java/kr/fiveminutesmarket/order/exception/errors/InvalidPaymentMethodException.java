package kr.fiveminutesmarket.order.exception.errors;

public class InvalidPaymentMethodException extends RuntimeException{

    public InvalidPaymentMethodException(String message) {
        super(message);
    }
}
