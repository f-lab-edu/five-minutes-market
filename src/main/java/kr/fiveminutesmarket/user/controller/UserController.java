package kr.fiveminutesmarket.user.controller;

import kr.fiveminutesmarket.common.dto.ResponseDto;
import kr.fiveminutesmarket.user.dto.dispatch.ContentDto;
import kr.fiveminutesmarket.user.dto.dispatch.mail.UserEmailRequestDto;
import kr.fiveminutesmarket.user.dto.dispatch.mail.UserInfoDto;
import kr.fiveminutesmarket.user.dto.request.SignInRequestDto;
import kr.fiveminutesmarket.user.dto.request.UserPasswordRequestDto;
import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequestDto;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.service.AuthService;
import kr.fiveminutesmarket.user.service.ContentService;
import kr.fiveminutesmarket.user.service.SendMailService;
import kr.fiveminutesmarket.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final SendMailService sendMailService;

    private final AuthService authService;

    private final ContentService mailContentService;

    public UserController(UserService userService,
                          SendMailService sendMailService,
                          AuthService authService,
                          @Qualifier("mailContentService") ContentService mailContentService) {
        this.userService = userService;
        this.sendMailService = sendMailService;
        this.authService = authService;
        this.mailContentService = mailContentService;
    }

    // 회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<UserResponseDto> signUp(@Valid @RequestBody UserRegistrationRequestDto resource) {

        UserResponseDto userResponseDto = userService.singUp(resource);

        return new ResponseDto<>(0, null, userResponseDto);
    }

    // 로그인
    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<?> signIn(@Valid @RequestBody SignInRequestDto resource, HttpSession session) {
        userService.signIn(resource, session);
        return new ResponseDto<>(0);
    }

    // 비밀번호 변경
    @PutMapping("/password")
    public ResponseDto<?> changePassword(@RequestBody UserPasswordRequestDto resource) {
         userService.updatePassword(resource.getEmail(), resource.getPassword());

         return new ResponseDto<>(0, "성공적으로 비밀번호를 변경하였습니다.");
    }

    // 비밀번호 찾기
    @PostMapping("/find-password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<String> findPassword(@Valid @RequestBody UserEmailRequestDto resource) {
        // 해당 이메일이 존재하는지 여부 검토
        UserInfoDto userInfoDto = authService.findByEmail(resource.getEmail());

        ContentDto mailContentDto
                = mailContentService.createContent(userInfoDto.getUserEmail(), userInfoDto.getUserName());
        /*
        * TODO
        *  - 인증키 저장 방식 수정필요
        *  - 비동기 방식으로 이메일 전송
        */
        sendMailService.sendMail(mailContentDto);

        return new ResponseDto<>(0, "성공적으로 사용자의 비밀번호 변경요청을 수행했습니다.", resource.getEmail());
    }

    // 비밀번호 초기화 키 유효성 검증
    @GetMapping("/reset-password/{key}")
    public ResponseDto<?> validateKey(@PathVariable("key") String key) {
        // TODO 세션 인증 방식 수정필요

        return new ResponseDto<>(0, "비밀번호 초기화 작업이 유효합니다.", null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<List<UserResponseDto>> findAll() throws UnknownHostException {
        return new ResponseDto<>(0, null, userService.findAll());
    }

    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<UserResponseDto> findUserInfo(@PathVariable("userName") String userName) {
        return new ResponseDto<>(0, null, userService.findByUserNameWithRole(userName));
    }
}
