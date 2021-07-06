package kr.fiveminutesmarket.user.scheduler;

import kr.fiveminutesmarket.user.event.ResetKeyBox;
import kr.fiveminutesmarket.user.repository.ResetKeyBoxRepository;
import kr.fiveminutesmarket.user.scheduler.message.mail.ContentDto;
import kr.fiveminutesmarket.user.scheduler.message.mail.MailContentService;
import kr.fiveminutesmarket.user.scheduler.message.mail.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Component
public class MessageRelayService {
    private static Logger logger = LoggerFactory.getLogger(MessageRelayService.class);

    private final ResetKeyBoxRepository resetKeyBoxRepository;

    private final SendMailService sendMailService;

    private final MailContentService mailContentService;

    public MessageRelayService(ResetKeyBoxRepository resetKeyBoxRepository,
                               SendMailService sendMailService,
                               MailContentService mailContentService) {
        this.resetKeyBoxRepository = resetKeyBoxRepository;
        this.sendMailService = sendMailService;
        this.mailContentService = mailContentService;
    }

    /**
     * 5분 주기로 ResetKeyBox 테이블 데이터를 기준으로 메일 전송
     *  - ResetKeyBox 조회: 모든 ResetKeyBox 데이터를 조회
     *  - 메일 발송: 발송완료되면 완료된 ResetKeyBox 리스트에 추가
     *  - ResetKeyBox 삭제: 완료된 ResetKeyBox 리스트를 기준으로 ResetKeyBox 테이블 내 데이터 삭제
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void schedulingResetPasswordMail() {
        List<ResetKeyBox> resetKeyBoxList = resetKeyBoxRepository.findAll();
        if (!resetKeyBoxList.isEmpty()) {
            List<Long> resetKeyBoxCompletedList = new LinkedList<>();
            resetKeyBoxList.forEach(resetKeyBox -> {
                ContentDto mailContent = mailContentService.createContent(
                        resetKeyBox.getResetKey(),
                        resetKeyBox.getEmail(),
                        resetKeyBox.getUserName()
                        );

                try {
                    sendMailService.sendMail(mailContent);
                    // 발송완료시 완료 목록에 추가
                    resetKeyBoxCompletedList.add(resetKeyBox.getResetKeyBoxId());
                } catch (MailException e) {
                    logger.error("MailSender process 과정에서 문제가 발생하였습니다. Box Id: " + resetKeyBox.getResetKeyBoxId());
                }
            });

            if(!resetKeyBoxCompletedList.isEmpty())
                resetKeyBoxRepository.deleteAllByResetKeyBoxId(resetKeyBoxCompletedList);
        }
    }
}
