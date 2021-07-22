package kr.fiveminutesmarket.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserPasswordRequestDto {

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대∙소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    public UserPasswordRequestDto() {
    }

    public UserPasswordRequestDto(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
