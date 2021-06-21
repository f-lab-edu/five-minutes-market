package kr.fiveminutesmarket.user.repository;

import kr.fiveminutesmarket.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserRepository {

    void insert(@Param("user") User user);

    List<User> findAll();

    User findById(@Param("userId") Long userId);

    User findByEmail(@Param("email") String email);

    User findByUserNameWithRole(@Param("userName") String userName);

    void updateUser(@Param("user") User user);

    void updateResetKey(String userEmail, String key, LocalDateTime expiredTime);

    String findKeyByEmail(String userEmail);

    User findByCheckKey(String checkKey);
}
