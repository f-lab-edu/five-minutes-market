package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.outbox.event.OutBoxEventBuilder;
import kr.fiveminutesmarket.user.domain.ResetPwKey;
import kr.fiveminutesmarket.user.event.ResetPwKeyCreated;
import kr.fiveminutesmarket.user.repository.ResetPwKeyRepository;
import kr.fiveminutesmarket.user.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserPasswordResetService {

    private final ResetPwKeyRepository resetKeyRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final OutBoxEventBuilder<ResetPwKeyCreated> eventBuilder;

    public UserPasswordResetService(ResetPwKeyRepository resetKeyRepository,
                                    ApplicationEventPublisher eventPublisher,
                                    OutBoxEventBuilder<ResetPwKeyCreated> eventBuilder) {
        this.resetKeyRepository = resetKeyRepository;
        this.eventPublisher = eventPublisher;
        this.eventBuilder = eventBuilder;
    }

    /**
     * 비밀번호 초기화 키 생성 후 DB UPDATE
     * @param userEmail 비밀번호 초기화 대상 사용자 이메일
     * @param userName
     */
    @Transactional
    public void saveResetKey(String userEmail, String userName) {
        String key = createResetKey();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredTime = now.plusMinutes(20);
        ResetPwKey resetPwKey = toResetPwKeyEntity(key, expiredTime, userEmail);
        resetKeyRepository.insertResetPwKey(resetPwKey);

        eventPublisher.publishEvent(
                eventBuilder.createOutBoxEvent(
                        new ResetPwKeyCreated(
                                resetPwKey.getResetPwKeyId(),
                                resetPwKey.getResetKey(),
                                resetPwKey.getExpireDate(),
                                resetPwKey.getEmail(),
                                userName)
                )
        );
    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }

    ResetPwKey toResetPwKeyEntity(String resetKey, LocalDateTime expireDate, String email) {
        return new ResetPwKey(resetKey, expireDate, email);
    }
}
