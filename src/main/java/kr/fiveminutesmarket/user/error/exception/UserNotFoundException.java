package kr.fiveminutesmarket.user.error.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("#" + id + " user is not found");
    }
}
