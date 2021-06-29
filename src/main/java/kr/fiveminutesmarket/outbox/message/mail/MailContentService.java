package kr.fiveminutesmarket.outbox.message.mail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.fiveminutesmarket.outbox.error.exception.PayloadConvertFailedException;
import kr.fiveminutesmarket.outbox.dto.ContentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    public ContentDto createContent(String payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;

        try {
            jsonNode = objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new PayloadConvertFailedException();
        }

        String userEmail = jsonNode.get("email").asText();
        String userName = jsonNode.get("user_name").asText();
        String resetKey = jsonNode.get("reset_key").asText();

        String title = "[Five Minutes Market] 임시 비밀번호 발급 안내 이메일";
        String passwordPath = domain + resetPath;
        String message = userName + " 님 안녕하세요.\n"
                + "하단 링크를 통해 패스워드를 초기화해주세요.\n"
                + passwordPath + resetKey;

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
