package kr.fiveminutesmarket.user.domain;

public class RoleType {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    private Long roleTypeId;

    private String roleTypeName;

    public RoleType() {
    }

    public RoleType(String roleName) {
        this.roleTypeName = roleName;
    }

    public Long getRoleTypeId() {
        return roleTypeId;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }
}
