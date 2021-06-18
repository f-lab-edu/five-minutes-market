package kr.fiveminutesmarket.user.service;

import kr.fiveminutesmarket.user.domain.RoleType;
import kr.fiveminutesmarket.user.domain.User;
import kr.fiveminutesmarket.user.dto.request.SignInRequestDto;
import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequestDto;
import kr.fiveminutesmarket.user.dto.response.RoleTypeResponseDto;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.error.exception.RoleTypeNotFoundException;
import kr.fiveminutesmarket.user.error.exception.UserEmailExistedException;
import kr.fiveminutesmarket.user.error.exception.UserNotFoundException;
import kr.fiveminutesmarket.user.repository.RoleTypeRepository;
import kr.fiveminutesmarket.user.repository.UserRepository;
import kr.fiveminutesmarket.user.security.JavaPasswordEncoder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleTypeRepository roleTypeRepository;

    private final JavaPasswordEncoder javaPasswordEncoder;
    private Environment environment;

    public UserService(UserRepository userRepository,
                       RoleTypeRepository roleTypeRepository,
                       JavaPasswordEncoder javaPasswordEncoder,
                       Environment environment) {
        this.userRepository = userRepository;
        this.roleTypeRepository = roleTypeRepository;
        this.javaPasswordEncoder = javaPasswordEncoder;
        this.environment = environment;
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

    public void signIn(SignInRequestDto resource, HttpSession session) {
        User user = Optional.ofNullable(userRepository.findByEmail(resource.getEmail()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        String password = resource.getPassword();
        String encodedPassword = user.getPassword();
        String salt = user.getSalt();

        if (!javaPasswordEncoder.matches(password, encodedPassword, salt)) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }

        session.setAttribute("email", user.getEmail());
        session.setAttribute("seller", user.getSeller());
        session.setAttribute("role", user.getRoleType());
    }

    public List<UserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    public void updatePassword(String userEmail, String password) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) throw new UserNotFoundException(userEmail);

        String salt = javaPasswordEncoder.generateSalt();
        user.updatePasswordWithSalt(javaPasswordEncoder.encode(password, salt), salt);

        userRepository.updateUser(user);
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
