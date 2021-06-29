package kr.fiveminutesmarket.outbox.domain;

public class OutBox {

    private Long outBoxId;
    private Long aggregateId;     // Grouping 위한 id
    private String aggregateType;   // 변경이 발생한 도메인 이름
    private String eventType;       // 발생한 이벤트
    private String payload;         // 도메인 entity 변경사항(JSON)

    public OutBox() {
    }

    public OutBox(Long aggregateId, String aggregateType, String eventType, String payload) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.eventType = eventType;
        this.payload = payload;
    }

    public Long getOutBoxId() {
        return outBoxId;
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
