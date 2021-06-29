package kr.fiveminutesmarket.user.repository;

import kr.fiveminutesmarket.user.domain.ResetPwKey;
import org.apache.ibatis.annotations.Param;

public interface ResetPwKeyRepository {
    void insertResetPwKey(@Param("resetPwKey") ResetPwKey resetPwKey);

    ResetPwKey findByResetKey(String key);
}
