package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.domain.RoleType;
import kr.fiveminutesmarket.user.domain.User;
import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequestDto;
import kr.fiveminutesmarket.user.dto.response.RoleTypeResponseDto;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.error.exception.RoleTypeNotFoundException;
import kr.fiveminutesmarket.user.error.exception.UserEmailExistedException;
import kr.fiveminutesmarket.user.error.exception.UserNotFoundException;
import kr.fiveminutesmarket.user.repository.RoleTypeRepository;
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
    private final RoleTypeRepository roleTypeRepository;

    private final JavaPasswordEncoder javaPasswordEncoder;

    public UserService(UserRepository userRepository,
                       RoleTypeRepository roleTypeRepository,
                       JavaPasswordEncoder javaPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleTypeRepository = roleTypeRepository;
        this.javaPasswordEncoder = javaPasswordEncoder;
    }

    public UserResponseDto singUp(UserRegistrationRequestDto resource) {

        if (userRepository.findByEmail(resource.getEmail()) != null) {
           throw new UserEmailExistedException(resource.getEmail());
        }

        String salt = javaPasswordEncoder.generateSalt();
        String password = resource.getPassword();

        RoleType roleType = roleTypeRepository.findByRoleTypeName(RoleType.DEFAULT_ROLE); // 회원가입시 기본 USER 권한
        if (roleType == null) {
            throw new RoleTypeNotFoundException(RoleType.DEFAULT_ROLE);
        }

        // 암호화된 password, salt, 기본 role -> User Entity insert
        User user = toUserEntity(
                resource,
                javaPasswordEncoder.encode(password, salt),
                roleType,
                salt);

        userRepository.insert(user);

        return toUserResponse(user);
    }

    public List<UserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDto findByUserNameWithRole(String userName) {
        User user = userRepository.findByUserNameWithRole(userName);

        if(user == null) {
            throw new UserNotFoundException(userName);
        }

        return toUserResponse(user);
    }

    private User toUserEntity(UserRegistrationRequestDto resource,
                              String encodedPassword,
                              RoleType roleType,
                              String salt) {
        return new User(
                resource.getUserName(),
                resource.getEmail(),
                encodedPassword,
                resource.getAddress(),
                resource.getPhoneNumber(),
                resource.getSeller(),
                roleType,
                salt);
    }

    private UserResponseDto toUserResponse(User entity) {
        return new UserResponseDto(
                entity.getUserId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getPhoneNumber(),
                entity.getSeller(),
                new RoleTypeResponseDto(entity.getRoleType().getRoleTypeName()));
    }

}