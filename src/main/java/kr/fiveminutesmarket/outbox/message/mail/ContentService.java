package kr.fiveminutesmarket.outbox.message.mail;

import kr.fiveminutesmarket.outbox.dto.ContentDto;

public interface ContentService {
    ContentDto createContent(String payload);
}
