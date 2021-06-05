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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
