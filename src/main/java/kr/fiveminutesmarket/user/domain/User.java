package kr.fiveminutesmarket.user.domain;

public class User {

    private Long userId;

    private String userName;

    private String email;

    private String password;

    private String address;

    private String phoneNumber;

    private Boolean seller;

    private RoleType roleType;

    private String salt;

    public User() {
    }

    public User(String userName,
                String email,
                String password,
                String address,
                String phoneNumber,
                Boolean seller,
                RoleType roleType,
                String salt) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.seller = seller;
        this.roleType = roleType;
        this.salt = salt;
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

    public RoleType getRoleType() {
        return roleType;
    }

    public String getSalt() {
        return salt;
    }

    public void updatePasswordWithSalt(String password, String salt) {
        this.password = password;
        this.salt = salt;
    }
}
