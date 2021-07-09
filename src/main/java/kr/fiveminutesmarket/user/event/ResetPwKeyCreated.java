package kr.fiveminutesmarket.user.event;

import java.time.LocalDateTime;

public class ResetPwKeyCreated {

    private Long resetPwKeyId;

    private String resetKey;

    private LocalDateTime expireDate;

    private String email;

    private String userName;

    private LocalDateTime occurredOn;

    public ResetPwKeyCreated() {
    }

    public ResetPwKeyCreated(String resetKey, LocalDateTime expireDate, String email, String userName, LocalDateTime occurredOn) {
        this.resetKey = resetKey;
        this.expireDate = expireDate;
        this.email = email;
        this.userName = userName;
        this.occurredOn = occurredOn;
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

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}
