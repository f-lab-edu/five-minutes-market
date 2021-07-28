package kr.fiveminutesmarket.user.controller;

import com.google.gson.Gson;
import kr.fiveminutesmarket.common.utils.RedisAuthUtils;
import kr.fiveminutesmarket.user.dto.dispatch.mail.UserEmailRequestDto;
import kr.fiveminutesmarket.user.dto.dispatch.mail.UserInfoDto;
import kr.fiveminutesmarket.user.dto.request.UserPasswordRequestDto;
import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequestDto;
import kr.fiveminutesmarket.user.dto.response.RoleTypeResponseDto;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.error.exception.ExpiredPasswordResetKeyException;
import kr.fiveminutesmarket.user.service.AuthService;
import kr.fiveminutesmarket.user.service.UserPasswordResetService;
import kr.fiveminutesmarket.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @Mock
    private UserPasswordResetService userPasswordResetService;

    @Mock
    private RedisAuthUtils redisUtils;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void signUpSuccess() throws Exception {
        final UserRegistrationRequestDto userRegistrationRequestDto = userRegistrationRequestDto();

        doReturn(new UserResponseDto(0L,
                "신재원",
                "hkx2r0i@gmail.com",
                "가나다라마바사",
                "01000000000",
                Boolean.TRUE,
                new RoleTypeResponseDto("ROLE_USER"))).when(userService).singUp(any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(userRegistrationRequestDto))
        );

        resultActions.andExpect(status().isCreated());
    }

    private UserRegistrationRequestDto userRegistrationRequestDto() {
        return new UserRegistrationRequestDto("신재원",
                "hkx2r0i@gmail.com",
                "xptmxm@0505",
                "가나다라마바사",
                "01000000000",
                true);
    }

    @Test
    @DisplayName("비밀번호 변경 테스트")
    void changePasswordSuccess() throws Exception {
        final UserPasswordRequestDto userPasswordRequestDto = userPasswordRequestDto();

        doNothing().when(userService).updatePassword(any(), any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/user/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(userPasswordRequestDto))
        );

        resultActions.andExpect(status().isOk());
    }

    private UserPasswordRequestDto userPasswordRequestDto() {
        return new UserPasswordRequestDto("hkx2r0i@gmail.com",
                "xptmxm@nae");
    }

    @Test
    @DisplayName("비밀번호 찾기 테스트")
    void findPasswordSuccess() throws Exception {
        final UserEmailRequestDto userEmailRequestDto = userEmailRequestDto();

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserEmail("hkx2r0i@gmail.com");
        userInfoDto.setUserName("테스트");

        doReturn(userInfoDto).when(authService).findByEmail(any());
        doNothing().when(userPasswordResetService).saveResetKey(any(), any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/find-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(userEmailRequestDto))
        );

        resultActions.andExpect(status().isOk());
    }

    private UserEmailRequestDto userEmailRequestDto() {
        return new UserEmailRequestDto("hkx2r0i@gmail.com");
    }

    @Test
    @DisplayName("비밀번호 초기화 키 유효성 검증 테스트")
    void validateKeySuccess() throws Exception {

        doNothing().when(authService).isValidResetKey(any(), any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/reset-password/testkey")
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("비밀번호 초기화 키 유효성 검증 실패 테스트")
    void validateKeyFail() throws Exception {

        doThrow(new ExpiredPasswordResetKeyException()).when(authService).isValidResetKey(any(), any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/reset-password/testkey")
        );

        resultActions.andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("유저 이름으로 유저 정보 조회 테스트")
    void findUserInfo() throws Exception {
        doReturn(new UserResponseDto(0L,
                "test",
                "hkx2r0i@gmail.com",
                "absfsdwfw",
                "01000000000",
                Boolean.TRUE,
                new RoleTypeResponseDto("ROLE_USER"))).when(userService).findByUserNameWithRole(any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/test")
        );

        resultActions.andExpect(status().isOk());
    }
}