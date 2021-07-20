package kr.fiveminutesmarket.user.scheduler.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    private final JavaMailSender mailSender;

    public SendMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendMail(ContentDto mailSendDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailSendDto.getTo());
        message.setSubject(mailSendDto.getTitle());
        message.setText(mailSendDto.getMessage());

        mailSender.send(message);
    }
}
