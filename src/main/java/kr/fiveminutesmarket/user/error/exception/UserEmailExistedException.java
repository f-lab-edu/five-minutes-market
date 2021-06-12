package kr.fiveminutesmarket.user.error.exception;

public class UserEmailExistedException extends RuntimeException {
    public UserEmailExistedException(String userEmail) {
        super("[" + userEmail + "] 계정은 이미 가입된 상태입니다.");
    }
}
