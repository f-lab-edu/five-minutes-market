package kr.fiveminutesmarket.user.dto.request;

public class UserRegistrationRequest {

    private String userName;

    private String email;

    private String password;

    private String address;

    private String phoneNumber;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(String userName, String email, String password, String address, String phoneNumber) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

}
