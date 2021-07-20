package kr.fiveminutesmarket.user.scheduler;

import kr.fiveminutesmarket.user.event.ResetKeyBox;
import kr.fiveminutesmarket.user.repository.ResetKeyBoxRepository;
import kr.fiveminutesmarket.user.scheduler.mail.ContentDto;
import kr.fiveminutesmarket.user.scheduler.mail.MailContentService;
import kr.fiveminutesmarket.user.scheduler.mail.SendMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ResetKeyBoxProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ResetKeyBoxProcessor.class);

    private final ResetKeyBoxRepository resetKeyBoxRepository;

    private final SendMailService sendMailService;

    private final MailContentService mailContentService;

    public ResetKeyBoxProcessor(ResetKeyBoxRepository resetKeyBoxRepository,
                                SendMailService sendMailService,
                                MailContentService mailContentService) {
        this.resetKeyBoxRepository = resetKeyBoxRepository;
        this.sendMailService = sendMailService;
        this.mailContentService = mailContentService;
    }

    public void processEachBoxesAndSendMail(List<ResetKeyBox> resetKeyBoxList) {
        if (!resetKeyBoxList.isEmpty()) {
            int totalSize = resetKeyBoxList.size();
            int numberOfCompleted = 0;
            List<Long> completedBoxesList = new LinkedList<>();
            for (ResetKeyBox resetKeyBox : resetKeyBoxList) {
                ContentDto mailContent = mailContentService.createContent(
                        resetKeyBox.getResetKey(),
                        resetKeyBox.getEmail(),
                        resetKeyBox.getUserName()
                );

                try {
                    // 발송완료시 ResetKeyBox 단위별로 mail 전송 및 table delete 처리
                    sendMailService.sendMail(mailContent);
                    logger.info("#" + resetKeyBox.getResetKeyBoxId() + " ResetKeyBox에 대한 메일 발송 완료하였습니다.");

                    completedBoxesList.add(resetKeyBox.getResetKeyBoxId());
                    numberOfCompleted++;

                    if (numberOfCompleted == 10 || numberOfCompleted == totalSize) {
                        resetKeyBoxRepository.deleteBoxesWithIds(completedBoxesList);
                        completedBoxesList.clear();
                        totalSize -= numberOfCompleted;
                        numberOfCompleted = 0;
                    }
                } catch (MailException e) {
                    logger.error("MailSender process 과정에서 문제가 발생하였습니다. Box Id: " + resetKeyBox.getResetKeyBoxId());
                } catch (RuntimeException e) {
                    logger.error("#" + resetKeyBox.getResetKeyBoxId() + " ResetKeyBox 처리하는 과정에서 예기치 않은 에러가 발생하였습니다.");
                }
            }
        }
    }
}
