package kr.fiveminutesmarket.user.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.user.dto.mail.MailSendDto;
import kr.fiveminutesmarket.user.dto.mail.UserEmailRequestDto;
import kr.fiveminutesmarket.user.dto.mail.UserInfoDto;
import kr.fiveminutesmarket.user.dto.request.UserPasswordRequestDto;
import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequestDto;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.error.exception.FailSendMailException;
import kr.fiveminutesmarket.user.service.AuthService;
import kr.fiveminutesmarket.user.service.SendMailService;
import kr.fiveminutesmarket.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final SendMailService sendMailService;

    private final AuthService authService;

    public UserController(UserService userService, SendMailService sendMailService, AuthService authService) {
        this.userService = userService;
        this.sendMailService = sendMailService;
        this.authService = authService;
    }

    // 회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<UserResponseDto> signUp(@Valid @RequestBody UserRegistrationRequestDto resource) throws URISyntaxException {

        UserResponseDto userResponseDto = userService.singUp(resource);

        return new ResponseDto<>(0, null, userResponseDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<UserResponseDto>> findAll() {
        return new ResponseDto<>(0, null, userService.findAll());
    }

    @PutMapping("/password")
    public ResponseDto<?> changePassword(@RequestBody UserPasswordRequestDto resource) {
         userService.updatePassword(resource.getEmail(), resource.getPassword());

         return new ResponseDto<>(0, "성공적으로 비밀번호를 변경하였습니다.");
    }

    @PostMapping("/find-password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<String> sendMailToResetPw(@Valid @RequestBody UserEmailRequestDto resource, HttpSession session) {
        // 해당 이메일이 존재하는지 여부 검토
        UserInfoDto userInfoDto = authService.findByEmail(resource.getEmail());

        Map<String, MailSendDto> mailSendResponse
                = sendMailService.createMailForResetPassword(userInfoDto.getUserEmail(), userInfoDto.getUserName());

        Iterator<String> iter =  mailSendResponse.keySet().iterator();
        if (iter.hasNext()) {
            String key = (String) iter.next();
            session.setAttribute(key, resource.getEmail());
            session.setMaxInactiveInterval(30*60);
            sendMailService.sendMail(mailSendResponse.get(key));
        } else {
            throw new FailSendMailException(resource.getEmail());
        }

        return new ResponseDto<>(0, "성공적으로 사용자의 비밀번호 변경요청을 수행했습니다.", resource.getEmail());
    }

    // key가 유효하면 비밀번호 변경 페이지 랜더링
    @GetMapping("/reset-password/{key}")
    public ResponseDto<?> checkValidPasswordKey(@PathVariable("key") String key, HttpSession session) {
        String userEmail = (String) session.getAttribute(key);
        // 유효한 key가 아닌 경우 exception 발생
        authService.isValidPasswordKey(userEmail);

        return new ResponseDto<>(0, "비밀번호 초기화 작업이 유효합니다.", null);
    }

    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<UserResponseDto> findUserInfo(@PathVariable("userName") String userName) {
        return new ResponseDto<>(0, null, userService.findByUserNameWithRole(userName));
    }
}
