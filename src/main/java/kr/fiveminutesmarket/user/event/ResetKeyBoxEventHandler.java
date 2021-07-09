package kr.fiveminutesmarket.user.event;

import kr.fiveminutesmarket.user.repository.ResetKeyBoxRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ResetKeyBoxEventHandler {

    private final ResetKeyBoxRepository resetKeyBoxRepository;

    public ResetKeyBoxEventHandler(ResetKeyBoxRepository resetKeyBoxRepository) {
        this.resetKeyBoxRepository = resetKeyBoxRepository;
    }

    @EventListener
    public void handleOutBoxEvent(ResetPwKeyCreated event) {
        ResetKeyBox resetKeyBox = new ResetKeyBox(
                event.getResetKey(),
                event.getEmail(),
                event.getUserName(),
                event.getOccurredOn()
        );

        resetKeyBoxRepository.insertResetKeyBox(resetKeyBox);
    }
}
