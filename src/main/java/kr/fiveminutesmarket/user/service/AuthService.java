package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.domain.User;
import kr.fiveminutesmarket.user.dto.dispatch.mail.UserInfoDto;
import kr.fiveminutesmarket.user.error.exception.ExpirePasswordKeyException;
import kr.fiveminutesmarket.user.error.exception.UserNotFoundException;
import kr.fiveminutesmarket.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void isValidPasswordKey(String userEmail) {
        if(userEmail == null) throw new ExpirePasswordKeyException();
    }

    private UserInfoDto toUserInfoDto(User user) {
        UserInfoDto dto = new UserInfoDto();
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getEmail());

        return dto;
    }
}
