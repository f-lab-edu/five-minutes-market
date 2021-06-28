package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.dto.dispatch.ContentDto;
import kr.fiveminutesmarket.user.repository.ResetPwKeyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class UserPasswordResetService {

    private final ResetPwKeyRepository resetKeyRepository;

    public UserPasswordResetService(ResetPwKeyRepository resetKeyRepository) {
        this.resetKeyRepository = resetKeyRepository;
    }

    /**
     * 비밀번호 초기화 키 생성 후 DB UPDATE
     * @param userEmail 비밀번호 초기화 대상 사용자 이메일
     */
    public void saveResetKey(String userEmail) {
        String key = createResetKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusMinutes(30);

        resetKeyRepository.insertResetPwKey(key, expiredTime, userEmail);

    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }
}
