package kr.fiveminutesmarket.user.event;

import kr.fiveminutesmarket.outbox.event.BaseEvent;

import java.time.LocalDateTime;

public class ResetPwKeyCreated extends BaseEvent {

    private Long resetPwKeyId;

    private String resetKey;

    private LocalDateTime expireDate;

    private String email;

    private String userName;

    public ResetPwKeyCreated() {
    }

    public ResetPwKeyCreated(Long resetPwKeyId, String resetKey, LocalDateTime expireDate, String email, String userName) {
        this.resetPwKeyId = resetPwKeyId;
        this.resetKey = resetKey;
        this.expireDate = expireDate;
        this.email = email;
        this.userName = userName;
    }

    public Long getResetPwKeyId() {
        return resetPwKeyId;
    }

    public String getResetKey() {
        return resetKey;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
