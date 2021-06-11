package kr.fiveminutesmarket.user.error.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName) {
        super("[" + userName + "] user is not found");
    }
}
