package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.dto.mail.MailSendDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class SendMailService {

    private final JavaMailSender mailSender;

    public SendMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MailSendDto createMailForResetPassword(String userEmail, String userName, String domain) {
        String key = createResetKey();
        String title = "[Five Minutes Market] 임시 비밀번호 발급 안내 이메일";
        String passwordPath = domain + "/user/reset-password/";
        String message = userName + " 님 안녕하세요.\n"
                + "하단 링크를 통해 패스워드를 초기화해주세요.\n"
                + passwordPath + key;

        return toMailSendDto(userEmail, title, message, key);
    }

    private String createResetKey() {
        return UUID.randomUUID().toString();
    }

    private MailSendDto toMailSendDto(String address, String title, String message, String key) {
        MailSendDto dto = new MailSendDto();
        dto.setAddress(address);
        dto.setTitle(title);
        dto.setMessage(message);
        dto.setKey(key);

        return dto;
    }

    public void sendMail(MailSendDto mailSendDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailSendDto.getAddress());
        message.setSubject(mailSendDto.getTitle());
        message.setText(mailSendDto.getMessage());

        mailSender.send(message);
    }
}
