package kr.fiveminutesmarket.user.error.exception;

public class RoleTypeNotFoundException extends RuntimeException {
    public RoleTypeNotFoundException(String roleType) {
        super("[" + roleType + "] Not Found");
    }
}
