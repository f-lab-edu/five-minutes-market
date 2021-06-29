package kr.fiveminutesmarket.outbox.event;

public class OutBoxEvent {
    private Long aggregateId;
    private String aggregateType;
    private String eventType;
    private String payload;

    public OutBoxEvent() {
    }

    public OutBoxEvent(Long aggregateId, String aggregateType, String eventType, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    public Long getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getPayload() {
        return payload;
    }
}
