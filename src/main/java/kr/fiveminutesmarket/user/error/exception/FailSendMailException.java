package kr.fiveminutesmarket.user.error.exception;

public class FailSendMailException extends RuntimeException{
    public FailSendMailException(String email) {
        super(email + " 계정에 대한 이메일 전송이 실패하였습니다.");
    }
}
