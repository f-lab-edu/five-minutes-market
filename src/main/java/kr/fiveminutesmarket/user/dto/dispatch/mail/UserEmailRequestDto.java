package kr.fiveminutesmarket.user.dto.dispatch.mail;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserEmailRequestDto {
    @NotNull(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않는 입력 값입니다.")
    @Length(max = 50, message = "이메일 길이는 50자 제한입니다.")
    private String email;

    public UserEmailRequestDto() {
    }

    public UserEmailRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
