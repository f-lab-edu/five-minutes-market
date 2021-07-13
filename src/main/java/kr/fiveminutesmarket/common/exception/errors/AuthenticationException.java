package kr.fiveminutesmarket.common.exception.errors;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}