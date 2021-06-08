package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.domain.User;
import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequest;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.error.exception.UserNotFoundException;
import kr.fiveminutesmarket.user.repository.UserRepository;
import kr.fiveminutesmarket.user.security.JavaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final JavaPasswordEncoder javaPasswordEncoder;

    public UserService(UserRepository userRepository, JavaPasswordEncoder javaPasswordEncoder) {
        this.userRepository = userRepository;
        this.javaPasswordEncoder = javaPasswordEncoder;
    }

    public UserResponseDto registerUser(UserRegistrationRequest resource) {
        String salt = javaPasswordEncoder.generateSalt();
        String password = resource.getPassword();

        String encodedPassword = javaPasswordEncoder.encode(password, salt);

        // 암호화된 password, salt 까지 User Entity에 담아서 insert
        User user = toEntity(resource, encodedPassword, salt);
        userRepository.insert(user);

        return toResponse(user);
    }

    public List<UserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id);

        if(user == null) {
            throw new UserNotFoundException(id);
        }

        return toResponse(user);
    }

    private User toEntity(UserRegistrationRequest resource, String encodedPassword, String salt) {
        return new User(
                resource.getUserName(),
                resource.getEmail(),
                encodedPassword,
                resource.getAddress(),
                resource.getPhoneNumber(),
                salt);
    }

    private UserResponseDto toResponse(User entity) {
        return new UserResponseDto(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getPhoneNumber());
    }

}
