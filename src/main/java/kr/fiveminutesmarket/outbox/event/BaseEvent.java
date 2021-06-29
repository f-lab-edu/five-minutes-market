package kr.fiveminutesmarket.outbox.event;

import java.time.LocalDateTime;

public abstract class BaseEvent {
    private LocalDateTime occurredOn;

    public BaseEvent() {
        this.occurredOn = LocalDateTime.now();
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

}
