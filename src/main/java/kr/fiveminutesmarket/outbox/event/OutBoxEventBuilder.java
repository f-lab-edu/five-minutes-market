package kr.fiveminutesmarket.outbox.event;

public interface OutBoxEventBuilder<T> {
    OutBoxEvent createOutBoxEvent(T domainEvent);
}
