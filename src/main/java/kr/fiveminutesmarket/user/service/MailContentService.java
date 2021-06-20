package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.dto.dispatch.ContentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailContentService implements ContentService{

    private final String domain;

    private final String resetPath;

    public MailContentService(@Value("${mail.domain}") String domain,
                              @Value("${mail.reset-path}") String resetPath) {
        this.domain = domain;
        this.resetPath = resetPath;
    }

    @Override
    public ContentDto createContent(String userEmail, String userName) {
        String key = createResetKey();
        String title = "[Five Minutes Market] 임시 비밀번호 발급 안내 이메일";
        String passwordPath = domain + resetPath;
        String message = userName + " 님 안녕하세요.\n"
                + "하단 링크를 통해 패스워드를 초기화해주세요.\n"
                + passwordPath + key;

        return toContentDto(userEmail, title, message);
    }

    // TODO key 생성 -> User DB 저장
    //  (따로 DB 저장하는 Service 오브젝트에 key 생성하는 로직을 붙여야 할 것으로 보입니다.)
    private String createResetKey() {
        return UUID.randomUUID().toString();
    }

    private ContentDto toContentDto(String address, String title, String message) {
        ContentDto dto = new ContentDto();
        dto.setTo(address);
        dto.setTitle(title);
        dto.setMessage(message);

        return dto;
    }
}
