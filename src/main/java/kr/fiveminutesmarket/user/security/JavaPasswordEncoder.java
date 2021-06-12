package kr.fiveminutesmarket.user.security;

public interface JavaPasswordEncoder {
    String encode(String password, String salt);

    Boolean matches(String password, String encodedPassword, String salt);

    String generateSalt();
}
