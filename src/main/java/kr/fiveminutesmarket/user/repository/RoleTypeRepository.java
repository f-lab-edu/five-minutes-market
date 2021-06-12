package kr.fiveminutesmarket.user.repository;

import kr.fiveminutesmarket.user.domain.RoleType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleTypeRepository {
    RoleType findByRoleTypeName(@Param("roleTypeName") String roleTypeName);
}
