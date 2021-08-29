package kr.fiveminutesmarket.user.scheduler;

import kr.fiveminutesmarket.user.event.ResetKeyBox;
import kr.fiveminutesmarket.user.repository.ResetKeyBoxRepository;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRelayScheduler {

    private final ResetKeyBoxRepository resetKeyBoxRepository;

    private final ResetKeyBoxProcessor resetKeyBoxProcessor;

    public MessageRelayScheduler(ResetKeyBoxRepository resetKeyBoxRepository,
                                 ResetKeyBoxProcessor resetKeyBoxProcessor) {
        this.resetKeyBoxRepository = resetKeyBoxRepository;
        this.resetKeyBoxProcessor = resetKeyBoxProcessor;
    }

    /**
     * 5분 주기로 ResetKeyBox 테이블 데이터를 기준으로 메일 전송
     *  - ResetKeyBox 조회: 모든 ResetKeyBox 데이터를 조회
     *  - 메일 발송: 발송완료되면 완료된 ResetKeyBox 리스트에 추가
     *  - ResetKeyBox 삭제: 완료된 ResetKeyBox 리스트를 기준으로 ResetKeyBox 테이블 내 데이터 삭제
     */
    //@Scheduled(cron = "*/15 * * * * *")
    //@SchedulerLock(name = "scheduledSendingEmailTask", lockAtLeastFor = "14s", lockAtMostFor = "14s")
    public void schedulingResetPasswordMail() {
        List<ResetKeyBox> resetKeyBoxList = resetKeyBoxRepository.findAll();
        // 리스트 결과값 기준으로 not empty
        resetKeyBoxProcessor.processEachBoxesAndSendMail(resetKeyBoxList);
    }
}
