package kr.fiveminutesmarket.user.dto.response;

public class RoleTypeResponseDto {

    private String roleName;

    public RoleTypeResponseDto() {
    }

    public RoleTypeResponseDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
