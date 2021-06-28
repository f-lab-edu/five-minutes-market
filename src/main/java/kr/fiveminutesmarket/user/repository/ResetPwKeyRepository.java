package kr.fiveminutesmarket.user.repository;

import kr.fiveminutesmarket.user.domain.ResetPwKey;

import java.time.LocalDateTime;

public interface ResetPwKeyRepository {
    void insertResetPwKey(String key, LocalDateTime expiredDate, String email);

    ResetPwKey findByResetKey(String key);
}
