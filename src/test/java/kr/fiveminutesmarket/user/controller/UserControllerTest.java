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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    @DisplayName("???????????? ?????? ?????????")
    void signUpSuccess() throws Exception {
        final UserRegistrationRequestDto userRegistrationRequestDto = userRegistrationRequestDto();

        doReturn(new UserResponseDto(0L,
                "?????????",
                "hkx2r0i@gmail.com",
                "?????????????????????",
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
        return new UserRegistrationRequestDto("?????????",
                "hkx2r0i@gmail.com",
                "xptmxm@0505",
                "?????????????????????",
                "01000000000",
                true);
    }

    @Test
    @DisplayName("???????????? ?????? ?????????")
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
        return new UserPasswordRequestDto("hkx2r0i@gmail.com");
    }

    @Test
    @DisplayName("???????????? ?????? ?????????")
    void findPasswordSuccess() throws Exception {
        final UserEmailRequestDto userEmailRequestDto = userEmailRequestDto();

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserEmail("hkx2r0i@gmail.com");
        userInfoDto.setUserName("?????????");

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
    @DisplayName("???????????? ????????? ??? ????????? ?????? ?????????")
    void validateKeySuccess() throws Exception {

        doNothing().when(authService).isValidResetKey(any(), any());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/reset-password/testkey")
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("???????????? ????????? ??? ????????? ?????? ?????? ?????????")
    void validateKeyFail() throws Exception {

        doThrow(new ExpiredPasswordResetKeyException()).when(authService).isValidResetKey(any(), any());

        assertThatThrownBy(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/user/reset-password/testkey")
            );
        }).hasCause(new ExpiredPasswordResetKeyException());
    }

    @Test
    @DisplayName("?????? ???????????? ?????? ?????? ?????? ?????????")
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