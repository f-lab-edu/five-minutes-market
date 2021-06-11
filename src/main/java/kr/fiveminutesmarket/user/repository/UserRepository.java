package kr.fiveminutesmarket.user.repository;

import kr.fiveminutesmarket.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {

    void insert(@Param("user") User user);

    List<User> findAll();

    User findById(@Param("userId") Long userId);

    User findByEmail(@Param("email") String email);

    User findByEmailWithRole(@Param("userName") String userName);
}
