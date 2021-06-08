package kr.fiveminutesmarket.user.dto.response;

public class UserResponseDto {

    private Long userId;

    private String userName;

    private String email;

    private String address;

    private String phoneNumber;

    public UserResponseDto() {
    }

    public UserResponseDto(Long userId, String userName, String email, String address, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
