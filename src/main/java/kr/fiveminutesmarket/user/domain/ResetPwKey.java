package kr.fiveminutesmarket.user.domain;

import java.time.LocalDateTime;

public class ResetPwKey {

    private Long resetPwKeyId;

    private String resetKey;

    private LocalDateTime expireDate;

    private String email;

    public ResetPwKey() {
    }

    public ResetPwKey(String resetKey, LocalDateTime expireDate, String email) {
        this.resetKey = resetKey;
        this.expireDate = expireDate;
        this.email = email;
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
}
