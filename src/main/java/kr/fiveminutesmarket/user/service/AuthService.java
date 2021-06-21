package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.domain.User;
import kr.fiveminutesmarket.user.dto.dispatch.mail.UserInfoDto;
import kr.fiveminutesmarket.user.error.exception.ExpiredPasswordResetKeyException;
import kr.fiveminutesmarket.user.error.exception.UserNotFoundException;
import kr.fiveminutesmarket.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UserNotFoundException(email);

        return toUserInfoDto(user);
    }

    /**
     * 비밀번호 초기화 인증키 유효성 검증
     * @param checkKey 초기화 URL query string 으로 받아온 인증키
     * @param now 초기화 URL 호출한 시각
     */
    public void isValidResetKey(String checkKey, LocalDateTime now) {
        User user = userRepository.findByCheckKey(checkKey);
        if(now.isAfter(user.getExpireDate()))
            throw new ExpiredPasswordResetKeyException();
    }

    private UserInfoDto toUserInfoDto(User user) {
        UserInfoDto dto = new UserInfoDto();
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getEmail());

        return dto;
    }
}
