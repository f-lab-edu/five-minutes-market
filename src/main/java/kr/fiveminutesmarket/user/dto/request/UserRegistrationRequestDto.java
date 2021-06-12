package kr.fiveminutesmarket.user.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegistrationRequestDto {

    @NotBlank(message = "사용자 이름은 필수 입력값입니다.")
    @Length(max = 50, message = "사용자 이름의 길이는 50자 제한입니다.")
    private String userName;

    @NotNull(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않는 입력 값입니다.")
    @Length(max = 50, message = "이메일 길이는 50자 제한입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대∙소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    @Length(message = "주소 길이는 100자 제한입니다.")
    private String address;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Length(message = "전화번호 길이는 11자 제한입니다.")
    @Pattern(regexp="^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$",
            message="전화번호 형식에 맞지 않는 입력 값입니다. '-' 제외하고 숫자만 입력해주세요.")
    private String phoneNumber;

    @NotNull(message = "seller: true 혹은 false 로 입력해주세요.")
    private Boolean seller;

    public UserRegistrationRequestDto() {
    }

    public UserRegistrationRequestDto(String userName,
                                      String email,
                                      String password,
                                      String address,
                                      String phoneNumber,
                                      Boolean seller) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.seller = seller;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getSeller() {
        return seller;
    }
}
