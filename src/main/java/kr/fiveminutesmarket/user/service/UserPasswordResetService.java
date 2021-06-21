package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class UserPasswordResetService {

    private final UserRepository userRepository;

    public UserPasswordResetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 비밀번호 초기화 키 생성 후 DB UPDATE
     * @param userEmail 비밀번호 초기화 대상 사용자 이메일
     */
    public void saveResetKey(String userEmail) {
        String key = createResetKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusMinutes(30);

        userRepository.updateResetKey(userEmail, key, expiredTime);
    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }
}
