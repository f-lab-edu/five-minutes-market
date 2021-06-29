package kr.fiveminutesmarket.outbox.scheduler;

import kr.fiveminutesmarket.outbox.domain.OutBox;
import kr.fiveminutesmarket.outbox.message.mail.ContentService;
import kr.fiveminutesmarket.outbox.message.mail.SendMailService;
import kr.fiveminutesmarket.outbox.repository.OutBoxRepository;
import kr.fiveminutesmarket.outbox.dto.ContentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
public class MessageRelayService {
    private static Logger logger = LoggerFactory.getLogger(MessageRelayService.class);

    private final OutBoxRepository outBoxRepository;

    private final SendMailService sendMailService;

    private final ContentService mailContentService;

    public MessageRelayService(OutBoxRepository outBoxRepository,
                               SendMailService sendMailService,
                               @Qualifier("mailContentService") ContentService mailContentService) {
        this.outBoxRepository = outBoxRepository;
        this.sendMailService = sendMailService;
        this.mailContentService = mailContentService;
    }

    /**
     * 5분 주기로 OutBox 테이블 데이터를 기준으로 메일 전송
     *  - OutBox 조회: 모든 OutBox 데이터를 조회
     *  - payload 파싱: OutBox 데이터 중 payload 가지고 MailContent 생성
     *  - 메일 발송: 발송완료되면 완료된 OutBox 리스트에 추가
     *  - OutBox 삭제: 완료된 OutBox 리스트를 기준으로 OutBox 테이블 내 데이터 삭제
     */
    @Transactional
    @Scheduled(cron = "0 */5 * * * *")
    public void schedulingResetPasswordMail() {
        List<OutBox> outBoxList = outBoxRepository.findAll();
        if (!outBoxList.isEmpty()) {
            List<Long> outBoxCompletedList = new LinkedList<>();
            outBoxList.forEach(outBox -> {
                String payload = outBox.getPayload();
                ContentDto mailContent = mailContentService.createContent(payload);

                try {
                    sendMailService.sendMail(mailContent);
                    // 발송완료시 완료 목록에 추가
                    outBoxCompletedList.add(outBox.getOutBoxId());
                } catch (MailException e) {
                    logger.error("MailSender process 과정에서 문제가 발생하였습니다.");
                }
            });

            if(!outBoxCompletedList.isEmpty())
                outBoxRepository.deleteAllByOutBoxId(outBoxCompletedList);
        }
    }
}
