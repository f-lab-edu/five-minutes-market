package kr.fiveminutesmarket.outbox.event;

import kr.fiveminutesmarket.outbox.domain.OutBox;
import kr.fiveminutesmarket.outbox.repository.OutBoxRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OutBoxEventHandler {

    private final OutBoxRepository outBoxRepository;

    public OutBoxEventHandler(OutBoxRepository outBoxRepository) {
        this.outBoxRepository = outBoxRepository;
    }

    @EventListener
    public void handleOutBoxEvent(OutBoxEvent event) {
        OutBox outBox = new OutBox(
                event.getAggregateId(),
                event.getAggregateType(),
                event.getEventType(),
                event.getPayload());

        outBoxRepository.insertOutBox(outBox);
    }
}
