package com.sessionhub.sessionhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String email;
    private String nickName;

}
