package kr.fiveminutesmarket.outbox.error.exception;

public class PayloadConvertFailedException extends RuntimeException{
    public PayloadConvertFailedException() {
        super("json 형태의 payload를 변환하는데 에러가 발생하였습니다.");
    }
}
