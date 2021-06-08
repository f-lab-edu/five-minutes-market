package kr.fiveminutesmarket.user.controller;

import kr.fiveminutesmarket.user.dto.request.UserRegistrationRequest;
import kr.fiveminutesmarket.user.dto.response.UserResponseDto;
import kr.fiveminutesmarket.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRegistrationRequest resource) throws URISyntaxException {

        UserResponseDto userResponseDto = userService.registerUser(resource);

        URI uri = new URI("/user" + userResponseDto.getUserId());
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

}
