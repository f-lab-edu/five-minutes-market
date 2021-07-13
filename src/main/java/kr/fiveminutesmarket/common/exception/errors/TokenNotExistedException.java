package kr.fiveminutesmarket.common.exception.errors;

public class TokenNotExistedException extends RuntimeException{
    public TokenNotExistedException() {
        super("Authorization 토큰값이 없습니다.");
    }
}
