package kr.fiveminutesmarket.user.dto.response;

import java.util.Date;

public class SignInResponseDto {

    private String email;

    private String token;

    private Date expiredDate;

    public SignInResponseDto(String email, String token, Date expiredDate) {
        this.email = email;
        this.token = token;
        this.expiredDate = expiredDate;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }
}
