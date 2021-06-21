package kr.fiveminutesmarket.user.error.exception;

public class ExpiredPasswordResetKeyException extends RuntimeException{
    public ExpiredPasswordResetKeyException() {
        super("비밀번호 초기화 유효시간이 만료되었습니다.");
    }
}
