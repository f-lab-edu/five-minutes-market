package kr.fiveminutesmarket.user.scheduler.message.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailContentService {

    private final String domain;

    private final String resetPath;

    public MailContentService(@Value("${mail.domain}") String domain,
                              @Value("${mail.reset-path}") String resetPath) {
        this.domain = domain;
        this.resetPath = resetPath;
    }

    public ContentDto createContent(String resetKey, String email, String userName) {
        String title = "[Five Minutes Market] 임시 비밀번호 발급 안내 이메일";
        String passwordPath = domain + resetPath;
        String message = userName + " 님 안녕하세요.\n"
                + "하단 링크를 통해 패스워드를 초기화해주세요.\n"
                + passwordPath + resetKey;

        return toContentDto(email, title, message);
    }

    private ContentDto toContentDto(String address, String title, String message) {
        ContentDto dto = new ContentDto();
        dto.setTo(address);
        dto.setTitle(title);
        dto.setMessage(message);

        return dto;
    }
}
