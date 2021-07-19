package kr.fiveminutesmarket.common.dto;

import kr.fiveminutesmarket.user.domain.RoleType;

public class UserSessionDto {

    private String email;

    private Boolean seller;

    private RoleType role;

    public UserSessionDto() {
    }

    public UserSessionDto(String email, Boolean seller, RoleType role) {
        this.email = email;
        this.seller = seller;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getSeller() {
        return seller;
    }

    public RoleType getRole() {
        return role;
    }

}
