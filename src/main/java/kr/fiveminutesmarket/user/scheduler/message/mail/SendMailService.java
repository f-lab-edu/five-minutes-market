package kr.fiveminutesmarket.user.scheduler.message.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    private final JavaMailSender mailSender;

    public SendMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(ContentDto mailSendDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailSendDto.getTo());
        message.setSubject(mailSendDto.getTitle());
        message.setText(mailSendDto.getMessage());

        mailSender.send(message);
    }
}
