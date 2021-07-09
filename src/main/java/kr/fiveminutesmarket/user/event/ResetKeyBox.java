package kr.fiveminutesmarket.user.event;

import java.time.LocalDateTime;

public class ResetKeyBox {
    private Long resetKeyBoxId;

    private String resetKey;

    private String email;

    private String userName;

    private LocalDateTime occurredOn;

    public ResetKeyBox() {
    }

    public ResetKeyBox(String resetKey, String email, String userName, LocalDateTime occurredOn) {
        this.resetKey = resetKey;
        this.email = email;
        this.userName = userName;
        this.occurredOn = occurredOn;
    }

    public Long getResetKeyBoxId() {
        return resetKeyBoxId;
    }

    public String getResetKey() {
        return resetKey;
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
