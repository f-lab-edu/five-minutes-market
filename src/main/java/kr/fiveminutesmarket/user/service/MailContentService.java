package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.dto.dispatch.ContentDto;
import kr.fiveminutesmarket.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailContentService implements ContentService{

    private final String domain;

    private final String resetPath;

    private final UserRepository userRepository;

    public MailContentService(@Value("${mail.domain}") String domain,
                              @Value("${mail.reset-path}") String resetPath,
                              UserRepository userRepository) {
        this.domain = domain;
        this.resetPath = resetPath;
        this.userRepository = userRepository;
    }

    @Override
    public ContentDto createContent(String userEmail, String userName) {
        // select key in user table
        String key = userRepository.findKeyByEmail(userEmail);

        String title = "[Five Minutes Market] 임시 비밀번호 발급 안내 이메일";
        String passwordPath = domain + resetPath;
        String message = userName + " 님 안녕하세요.\n"
                + "하단 링크를 통해 패스워드를 초기화해주세요.\n"
                + passwordPath + key;

        return toContentDto(userEmail, title, message);
    }

    private ContentDto toContentDto(String address, String title, String message) {
        ContentDto dto = new ContentDto();
        dto.setTo(address);
        dto.setTitle(title);
        dto.setMessage(message);

        return dto;
    }
}
