package com.sessionhub.sessionhub.domain.service;

import com.sessionhub.sessionhub.domain.entity.Role;
import com.sessionhub.sessionhub.domain.entity.User;
import com.sessionhub.sessionhub.domain.repository.UserRepository;
import com.sessionhub.sessionhub.dto.AddUserRequestDto;
import com.sessionhub.sessionhub.dto.AddUserResponseDto;
import com.sessionhub.sessionhub.dto.LoginRequestDto;
import com.sessionhub.sessionhub.dto.LoginResponseDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public AddUserResponseDto signUp(AddUserRequestDto requestDto) {
        if (userRepository.findByNickName(requestDto.getNickName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        String encodedPassword = encoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getNickName(), encodedPassword, requestDto.getEmail(), Role.ROLE_USER);

        userRepository.save(user);

        AddUserResponseDto responseDto = new AddUserResponseDto(user);

        return responseDto;
    }

    public LoginResponseDto logIn(LoginRequestDto requestDto) {

        Optional<User> userOptional = userRepository.findByEmail(requestDto.getEmail());

        if (userOptional.isEmpty() || !passwordEncoder.matches(requestDto.getPassword(), userOptional.get().getPassword())){
            throw new IllegalArgumentException("유효하지 않은 이메일 또는 비밀번호 입니다.");
        }

        User user = userOptional.get();
        return new LoginResponseDto(user.getId(), user.getEmail(), user.getNickName());
    }
}
