package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.dto.dispatch.ContentDto;

public interface ContentService {
    ContentDto createContent(String target, String name);
}
