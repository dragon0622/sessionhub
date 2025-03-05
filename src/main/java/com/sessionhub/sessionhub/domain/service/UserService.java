package com.sessionhub.sessionhub.domain.service;

import com.sessionhub.sessionhub.domain.entity.Role;
import com.sessionhub.sessionhub.domain.entity.User;
import com.sessionhub.sessionhub.domain.repository.UserRepository;
import com.sessionhub.sessionhub.dto.AddUserRequestDto;
import com.sessionhub.sessionhub.dto.AddUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

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
}
