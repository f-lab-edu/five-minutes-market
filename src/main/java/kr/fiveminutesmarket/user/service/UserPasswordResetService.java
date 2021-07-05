package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.domain.ResetPwKey;
import kr.fiveminutesmarket.user.event.ResetPwKeyCreated;
import kr.fiveminutesmarket.user.repository.ResetPwKeyRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserPasswordResetService {

    private final ResetPwKeyRepository resetKeyRepository;

    private final ApplicationEventPublisher eventPublisher;

    public UserPasswordResetService(ResetPwKeyRepository resetKeyRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.resetKeyRepository = resetKeyRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * 비밀번호 초기화 키 생성 후 DB UPDATE
     *
     * @param userEmail 비밀번호 초기화 대상 사용자 이메일
     * @param userName 비밀번호 초기화 대상 사용자 이름
     */
    @Transactional
    public void saveResetKey(String userEmail, String userName) {
        String key = createResetKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusMinutes(20);
        ResetPwKey resetPwKey = toResetPwKeyEntity(key, expiredTime, userEmail);
        resetKeyRepository.insertResetPwKey(resetPwKey);

        eventPublisher.publishEvent(
                new ResetPwKeyCreated(
                        resetPwKey.getResetKey(),
                        resetPwKey.getExpireDate(),
                        resetPwKey.getEmail(),
                        userName,
                        LocalDateTime.now())
        );
    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }

    ResetPwKey toResetPwKeyEntity(String resetKey, LocalDateTime expireDate, String email) {
        return new ResetPwKey(resetKey, expireDate, email);
    }
}
