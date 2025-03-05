package com.sessionhub.sessionhub.domain.controller;

import com.sessionhub.sessionhub.domain.service.UserService;
import com.sessionhub.sessionhub.dto.AddUserRequestDto;
import com.sessionhub.sessionhub.dto.AddUserResponseDto;
import com.sessionhub.sessionhub.dto.LoginRequestDto;
import com.sessionhub.sessionhub.dto.LoginResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<AddUserResponseDto> signUp(@RequestBody AddUserRequestDto userDto) {

        AddUserResponseDto responseDto = userService.signUp(userDto);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logIn")
    public ResponseEntity<LoginResponseDto> logIn(@RequestBody LoginRequestDto requestDto, HttpSession session) {

        LoginResponseDto responseDto = userService.logIn(requestDto);

        session.setAttribute("user", responseDto);

        return ResponseEntity.ok(responseDto);
    }
}
