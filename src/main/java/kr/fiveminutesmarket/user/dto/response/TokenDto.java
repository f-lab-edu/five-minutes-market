package kr.fiveminutesmarket.user.dto.response;

public class TokenDto {
    private String token;

    public TokenDto() {
    }

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
