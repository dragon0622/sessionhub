package com.sessionhub.sessionhub.domain.controller;

import com.sessionhub.sessionhub.domain.service.UserService;
import com.sessionhub.sessionhub.dto.AddUserRequestDto;
import com.sessionhub.sessionhub.dto.AddUserResponseDto;
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
}
